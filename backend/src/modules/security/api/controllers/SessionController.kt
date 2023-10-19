package com.davintiproject.backend.modules.security.api.controllers

import com.davintiproject.backend.data.entities.User
import com.davintiproject.backend.modules.security.services.TokenService
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

data class LoginParams (
    val email: String,
    val password: String
)

data class RegisterParams (
    val name: String,
    val email: String,
    val password: String,
    val isAdmin: Boolean
)

data class TokenDto(val accessToken: String)

@RestController
@RequestMapping("/session")
class SessionController(
    private val authManager: AuthenticationManager,
    private val tokenService: TokenService
) {

    @PostMapping
    @PreAuthorize("permitAll()")
    fun login(@RequestBody params: LoginParams): ResponseEntity<TokenDto> {
        val userPassword = UsernamePasswordAuthenticationToken(params.email, params.password)

        val auth = authManager.authenticate(userPassword)

        val token = tokenService.generateToken(auth.principal as User)

        return ResponseEntity.ok(TokenDto(token))
    }
}