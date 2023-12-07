package com.davintiproject.backend.modules.security.domain.commands

import com.davintiproject.backend.common.domain.Command
import com.davintiproject.backend.modules.security.data.repositories.TokenRepository
import com.davintiproject.backend.modules.security.data.repositories.UserRepository
import com.davintiproject.backend.modules.security.domain.entities.User
import com.davintiproject.backend.modules.security.services.JwtService
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Component
import org.springframework.web.server.ResponseStatusException
import kotlin.jvm.optionals.getOrNull


data class RefreshTokenDto(
    val refreshToken: String,
)

@Component
class RefreshTokenCommand(
    val userRepository: UserRepository,
    val tokenService: JwtService,
    val revokeTokens: RevokeAllUserTokens,
    val saveToken: SaveTokenCommand,
    val tokenRepository: TokenRepository
) : Command<RefreshTokenDto, TokenPair> {
    override fun execute(params: RefreshTokenDto): TokenPair {
        val userEmail = tokenService.extractUserEmail(params.refreshToken)

        if (!tokenService.isTokenValid(params.refreshToken)) {
            throw ResponseStatusException(HttpStatus.UNAUTHORIZED, "JWT Token invalid")
        }

        val user: User = userRepository.findByEmail(userEmail)
            ?: throw ResponseStatusException(HttpStatus.UNAUTHORIZED, "User not found")

        val storedToken = tokenRepository.findByToken(params.refreshToken).getOrNull()

        if (storedToken == null || storedToken.expired || storedToken.revoked) {
            throw ResponseStatusException(HttpStatus.UNAUTHORIZED, "JWT Token invalid")
        }

        val newAccessToken = tokenService.generateToken(user)
        val newRefreshToken = tokenService.generateRefreshToken(user)

        revokeTokens.execute(user.id)
        saveToken.execute(SaveTokenDto(user, newRefreshToken))

        return TokenPair(newAccessToken, newRefreshToken)
    }
}