package com.davintiproject.backend.modules.security.domain.entities

import jakarta.persistence.*


enum class TokenType {
    BEARER
}

@Entity
data class Token(
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    val id: Int = 0,

    @Column(unique = true)
    val token: String = "",

    @Enumerated(EnumType.STRING)
    val tokenType: TokenType = TokenType.BEARER,

    val revoked: Boolean = false,
    val expired: Boolean = false,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    val user: User? = null
)
