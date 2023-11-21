package com.davintiproject.backend.modules.security.domain.commands

data class TokenPair(
    val accessToken: String, val refreshToken: String
)