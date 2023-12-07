package com.davintiproject.backend.modules.security.domain.helpers

import jakarta.servlet.http.HttpServletRequest
import org.springframework.http.HttpHeaders
import java.util.*

object AuthorizationHeaderHelper {
    fun getAuthorizationToken(request: HttpServletRequest): Optional<String> {
        val authHeader: String? = request.getHeader(HttpHeaders.AUTHORIZATION)

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return Optional.empty()
        }

        return Optional.of(authHeader.substring(7))
    }
}