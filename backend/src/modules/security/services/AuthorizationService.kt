package com.davintiproject.backend.modules.security.services

import com.davintiproject.backend.modules.security.data.repositories.UserRepository
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service

@Service
class AuthorizationService(
    private val userRepository: UserRepository
) : UserDetailsService {
    override fun loadUserByUsername(username: String?): UserDetails? {
        return userRepository.findByEmail(username ?: "")
    }
}