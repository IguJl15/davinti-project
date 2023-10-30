package com.davintiproject.backend.data.repositories

import com.davintiproject.backend.modules.security.data.entities.User
import org.springframework.data.repository.CrudRepository
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Repository as RepositoryAnnotation

@RepositoryAnnotation
interface UserRepository : CrudRepository<User, String> {
    fun findByEmail(email: String): UserDetails?
}