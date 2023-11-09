package com.davintiproject.backend.modules.security.domain.commands

import com.davintiproject.backend.common.domain.Command
import com.davintiproject.backend.modules.security.data.repositories.UserRepository
import modules.security.services.JwtService
import org.springframework.http.HttpStatus
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.web.server.ResponseStatusException


data class LoginDto(
    val email: String,
    val password: String
)

class LoginCommand(
    private val saveToken: SaveTokenCommand,
    private val tokenService: JwtService,
    private val userRepository: UserRepository,
    private val authManager: AuthenticationManager
) : Command<LoginDto, TokenPair> {
    override fun execute(params: LoginDto): TokenPair {
        authManager.authenticate(UsernamePasswordAuthenticationToken(params.email, params.password))

        val user = userRepository.findByEmail(params.email) ?: throw ResponseStatusException(HttpStatus.NOT_FOUND)

        val accessToken = tokenService.generateToken(user)
        val refreshToken = tokenService.generateRefreshToken(user)

        saveToken.execute(SaveTokenDto(user, refreshToken))

        return TokenPair(accessToken, refreshToken)
    }
}