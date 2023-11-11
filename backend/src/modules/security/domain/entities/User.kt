package com.davintiproject.backend.modules.security.domain.entities

import com.fasterxml.jackson.annotation.JsonIgnore
import jakarta.persistence.*
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

enum class UserRole {
    user, admin, instructor
}

@Entity(name = "users")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
open class User(
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    val id: Int = 0,

    @Column(nullable = false)
    open val completeName: String = "",

    @Column(nullable = false, unique = true)
    open val email: String = "",

    @Column(nullable = false)
    @JsonIgnore
    open var pass: String = "",

    @Column(nullable = false)
    open val role: UserRole = UserRole.user,

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    @JsonIgnore
    open val tokens: List<Token> = emptyList()
) : UserDetails {


    @Transient
    override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
        return when (role) {
            UserRole.user -> mutableListOf(SimpleGrantedAuthority("ROLE_STUDENT"))
            UserRole.instructor -> mutableListOf(SimpleGrantedAuthority("ROLE_INSTRUCTOR"))
            UserRole.admin -> mutableListOf(SimpleGrantedAuthority("ROLE_ADMIN"))
        }
    }

    @Transient
    @JsonIgnore
    override fun getPassword(): String {
        return pass
    }

    @Transient
    @JsonIgnore
    override fun getUsername(): String {
        return email
    }

    @JsonIgnore
    override fun isAccountNonExpired(): Boolean = true

    @JsonIgnore
    override fun isAccountNonLocked(): Boolean = true

    @JsonIgnore
    override fun isCredentialsNonExpired(): Boolean = true

    @JsonIgnore
    override fun isEnabled(): Boolean = true
}