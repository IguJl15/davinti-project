package com.davintiproject.backend.modules.security.domain.commands

import com.davintiproject.backend.common.domain.Command
import com.davintiproject.backend.modules.security.data.repositories.UserRepository
import com.davintiproject.backend.modules.security.domain.entities.User
import com.davintiproject.backend.modules.security.domain.helpers.AuthorizationHeaderHelper
import jakarta.servlet.http.HttpServletRequest
import modules.security.services.JwtService
import org.springframework.http.HttpStatus
import org.springframework.web.server.ResponseStatusException


data class RefreshTokenDto(
    val request: HttpServletRequest,
)

class RefreshTokenCommand(
    val userRepository: UserRepository,
    val tokenService: JwtService,
    val revokeTokens: RevokeAllUserTokens,
    val saveToken: SaveTokenCommand
) : Command<RefreshTokenDto, TokenPair> {
    override fun execute(params: RefreshTokenDto): TokenPair {
        val authHeader = AuthorizationHeaderHelper.getAuthorizationToken(request = params.request)

        if (authHeader.isEmpty) {
            // TODO: Check status and add message
            throw ResponseStatusException(HttpStatus.BAD_REQUEST)
        }

        val refreshToken: String = authHeader.get()

        val userId = tokenService.extractUserId(refreshToken).toInt()

        // TODO: Find by email
        val user: User = userRepository.findAll().find { it.id == userId }
            ?: throw ResponseStatusException(HttpStatus.BAD_REQUEST)


        if (!tokenService.isTokenValid(refreshToken, user)) {
            throw ResponseStatusException(HttpStatus.BAD_REQUEST, "Token invalid")
        }

        val newAccessToken = tokenService.generateToken(user)
        val newRefreshToken = tokenService.generateRefreshToken(user)

        revokeTokens.execute(user.id)
        saveToken.execute(SaveTokenDto(user, newRefreshToken))

        return TokenPair(newAccessToken, newRefreshToken)
    }
}