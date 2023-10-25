package com.davintiproject.backend.modules.users.api.controllers

import com.davintiproject.backend.data.entities.User
import com.davintiproject.backend.data.entities.UserRole
import com.davintiproject.backend.data.repositories.UserRepository
import com.davintiproject.backend.modules.security.api.controllers.RegisterParams
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.web.bind.annotation.*
import java.net.URI

@RestController
@RequestMapping("/")
class UsersController(
    val userRepository: UserRepository
) {
    @PostMapping("users")
    @PreAuthorize("isAnonymous()")
    @ResponseStatus(HttpStatus.CREATED)
    fun createUser(@RequestBody params: RegisterParams): ResponseEntity<String> {
        if (userRepository.findByEmail(params.email) != null) return ResponseEntity.badRequest().build()

        val encryptedPassword = BCryptPasswordEncoder().encode(params.password)

        val newUser = User(
            "",
            params.name,
            params.email,
            encryptedPassword,
            if (params.isAdmin) UserRole.admin else UserRole.user
        )

        val savedUser = userRepository.save(newUser)

        return ResponseEntity.created(URI.create("/users/${savedUser.id}")).build()
    }

    @GetMapping("users")
    @PreAuthorize("hasRole('ADMIN')")
    fun listAllUsers(): ResponseEntity<List<User>> {
        val users = userRepository.findAll()

        return ResponseEntity.ok(users.toList())
    }


    @GetMapping("users/{id}")
    @PreAuthorize("hasRole('ADMIN') or authentication.principal.id == #id")
    fun findUserById(@PathVariable id: String): ResponseEntity<User> {
        val user = userRepository.findById(id)

        if (user.isEmpty) return ResponseEntity.notFound().build()

        return ResponseEntity.ok(user.get())
    }
}