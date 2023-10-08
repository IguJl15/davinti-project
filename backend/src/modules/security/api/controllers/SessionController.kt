package com.davintiproject.backend.api.controllers

import com.davintiproject.backend.data.entities.User
import com.davintiproject.backend.data.entities.UserRole
import com.davintiproject.backend.data.repositories.UserRepository
import com.davintiproject.backend.modules.security.services.TokenService
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
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
    private val userRepository: UserRepository,
    private val tokenService: TokenService
) {

    @PostMapping("")
    @PreAuthorize("permitAll()")
    fun login(@RequestBody params: LoginParams): ResponseEntity<TokenDto> {
        val userPassword = UsernamePasswordAuthenticationToken(params.email, params.password)

        val auth = authManager.authenticate(userPassword)

        val token = tokenService.generateToken(auth.principal as User)

        return ResponseEntity.ok(TokenDto(token))
    }

    @PostMapping("/new")
    @PreAuthorize("permitAll()")
    fun register(@RequestBody params: RegisterParams): ResponseEntity<String> {
        if(userRepository.findByEmail(params.email) != null) return ResponseEntity.badRequest().build()

        val encryptedPassword = BCryptPasswordEncoder().encode(params.password)

        val newUser = User(
            "",
            params.name,
            params.email,
            encryptedPassword,
            if (params.isAdmin) UserRole.admin else UserRole.user
        )

        userRepository.save(newUser)

        return ResponseEntity.ok().build()
    }
}