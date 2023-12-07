package com.davintiproject.backend.modules.security.api.controllers

import com.davintiproject.backend.modules.security.domain.commands.*
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/session")
@PreAuthorize("isAnonymous()")
class SessionController(
    private val loginCommand: LoginCommand, private val refreshToken: RefreshTokenCommand
) {
    @PostMapping
    fun login(@RequestBody params: LoginDto): ResponseEntity<TokenPair> {
        val tokens = loginCommand.execute(params)

        return ResponseEntity.ok(tokens)
    }


    @PostMapping("/refresh")
    fun refreshTokens(@RequestBody params: RefreshTokenDto): ResponseEntity<TokenPair> {
        val tokens = refreshToken.execute(params)

        return ResponseEntity.ok(tokens)
    }
}