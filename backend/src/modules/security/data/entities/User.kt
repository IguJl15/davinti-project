package com.davintiproject.backend.data.entities

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

enum class UserRole {
    user, admin
}

@Entity(name = "users")
class User(
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    val id: String = "",
    @Column(nullable = false)
    val userName: String = "",
    @Column(nullable = false, unique = true)
    val email: String = "",
    @Column(nullable = false)
    val pass: String = "",
    @Column(nullable = false)
    val role: UserRole = UserRole.user
) : UserDetails {

    override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
        return when (role) {
            UserRole.user -> mutableListOf(SimpleGrantedAuthority("ROLE_USER"))
            UserRole.admin -> mutableListOf(SimpleGrantedAuthority("ROLE_ADMIN"), SimpleGrantedAuthority("ROLE_USER"))
        }
    }

    override fun getPassword(): String {
        return pass
    }

    override fun getUsername(): String {
        return userName
    }

    override fun isAccountNonExpired(): Boolean = true

    override fun isAccountNonLocked(): Boolean = true

    override fun isCredentialsNonExpired(): Boolean = true

    override fun isEnabled(): Boolean = true
}