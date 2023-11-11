package com.davintiproject.backend.modules.security.domain.entities

import jakarta.persistence.*
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

enum class UserRole {
    user, admin, instructor
}

@Entity(name = "users")
open class User(
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    val id: Int = 0,

    @Column(nullable = false)
    open val completeName: String = "",

    @Column(nullable = false, unique = true)
    open val email: String = "",

    @Column(nullable = false)
    open var pass: String = "",

    @Column(nullable = false)
    val role: UserRole = UserRole.user,

    @OneToMany(mappedBy = "user")
    val tokens: List<Token> = emptyList()
) : UserDetails {

    override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
        return when (role) {
            UserRole.user -> mutableListOf(SimpleGrantedAuthority("ROLE_STUDENT"))
            UserRole.instructor -> mutableListOf(SimpleGrantedAuthority("ROLE_INSTRUCTOR"))
            UserRole.admin -> mutableListOf(SimpleGrantedAuthority("ROLE_ADMIN"))
        }
    }

    @Transient
    override fun getPassword(): String {
        return pass
    }

    @Transient
    override fun getUsername(): String {
        return email
    }

    override fun isAccountNonExpired(): Boolean = true

    override fun isAccountNonLocked(): Boolean = true

    override fun isCredentialsNonExpired(): Boolean = true

    override fun isEnabled(): Boolean = true
}