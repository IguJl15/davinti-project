package com.davintiproject.backend.modules.security.api.controllers

import com.davintiproject.backend.modules.security.domain.entities.User
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("")
class IndexController {
    @GetMapping("/public")
    @PreAuthorize("permitAll()")
    fun helloWorld(): String {
        return "Hello anonimo"
    }

    @GetMapping("/admin")
    @PreAuthorize("hasRole('ADMIN')")
    fun admin(): ResponseEntity<String> {
        val user = SecurityContextHolder.getContext().authentication.principal as User

        return ResponseEntity.ok("Hello admin ${user.completeName}")
    }

    @GetMapping("/user")
    @PreAuthorize("hasRole('USER')")
    fun user(): ResponseEntity<String> {
        val user = SecurityContextHolder.getContext().authentication.principal as User

        return ResponseEntity.ok("Hello user ${user.completeName}")
    }
}