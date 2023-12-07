package com.davintiproject.backend.modules.security.services

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import com.auth0.jwt.exceptions.JWTCreationException
import com.auth0.jwt.exceptions.JWTVerificationException
import com.auth0.jwt.interfaces.DecodedJWT
import com.davintiproject.backend.modules.security.domain.entities.User
import org.springframework.stereotype.Service
import java.time.Duration
import java.time.Instant

@Service
class TokenService {
    fun generateToken(user: User): String {
        try {
            val algorithm = Algorithm.HMAC256("SUPERSECRET")

            return JWT.create()
                .withSubject(user.id.toString())
                .withClaim("name", user.completeName)
                .withClaim("email", user.email)
                .withClaim("roles", user.role.name)
                .withIssuer("auth-api")
                .withExpiresAt(Instant.now().plus(Duration.ofHours(1)))
                .sign(algorithm)
        } catch (e: JWTCreationException) {
            throw RuntimeException("Error while creating JWT Token", e)
        }
    }

    fun validateToken(token: String): DecodedJWT? {
        val algorithm = Algorithm.HMAC256("SUPERSECRET")

        try {
            return JWT.require(algorithm)
                .withIssuer("auth-api")
                .build()
                .verify(token)
        } catch (e: JWTVerificationException) {
            return null
        }
    }
}