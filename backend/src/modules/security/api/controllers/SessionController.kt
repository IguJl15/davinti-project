package com.davintiproject.backend.modules.security.api.controllers

import com.davintiproject.backend.modules.security.domain.commands.LoginCommand
import com.davintiproject.backend.modules.security.domain.commands.LoginDto
import com.davintiproject.backend.modules.security.domain.commands.TokenPair
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
    private val loginCommand: LoginCommand
) {

    @PostMapping
    fun login(@RequestBody params: LoginDto): ResponseEntity<TokenPair> {
        return try {
            val tokens = loginCommand.execute(params)

            ResponseEntity.ok(tokens)
        } catch (error: Throwable) {
            ResponseEntity.notFound().build()
        }
    }
}