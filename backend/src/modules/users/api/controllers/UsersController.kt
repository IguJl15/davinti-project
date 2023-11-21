package com.davintiproject.backend.modules.users.api.controllers


import com.davintiproject.backend.modules.security.data.repositories.UserRepository
import com.davintiproject.backend.modules.security.domain.entities.User
import com.davintiproject.backend.modules.security.domain.entities.UserRole
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.web.bind.annotation.*
import java.net.URI


data class RegisterParams(
    val name: String,
    val email: String,
    val password: String,
    val isAdmin: Boolean = false
)


@RestController
@RequestMapping("/")
class UsersController(
    val userRepository: UserRepository
) {
    @PostMapping("users")
    @ResponseStatus(HttpStatus.CREATED)
    // @PreAuthorize("hasRole('ADMIN')")
    fun createUser(@RequestBody params: RegisterParams): ResponseEntity<String> {
        if (userRepository.findByEmail(params.email) != null) return ResponseEntity.badRequest()
            .body("Email already in use")

        val encryptedPassword = BCryptPasswordEncoder().encode(params.password)

        val newUser = User(
            0,
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
    fun findUserById(@PathVariable id: Int): ResponseEntity<User> {
        val user = userRepository.findById(id)

        if (user.isEmpty) return ResponseEntity.notFound().build()

        return ResponseEntity.ok(user.get())
    }

    @GetMapping("me")
    fun me(): ResponseEntity<User> {
        val user = SecurityContextHolder.getContext().authentication.principal as User

        val userSaved = userRepository.findById(user.id)

        if (userSaved.isEmpty) return ResponseEntity.notFound().build()

        return ResponseEntity.ok(userSaved.get())
    }
}