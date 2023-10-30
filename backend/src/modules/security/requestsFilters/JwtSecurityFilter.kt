package com.davintiproject.backend.modules.security.requestsFilters

import com.auth0.jwt.interfaces.DecodedJWT
import com.davintiproject.backend.modules.security.data.entities.User
import com.davintiproject.backend.modules.security.data.entities.UserRole
import com.davintiproject.backend.modules.security.services.TokenService
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter

@Component
class JwtSecurityFilter(
    val jwtService: TokenService
) : OncePerRequestFilter() {
    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        val token = recoverToken(request)
        if (token.isNullOrBlank()) return filterChain.doFilter(request, response)

        val decodedToken = jwtService.validateToken(token) ?: return filterChain.doFilter(request, response)

        val user = userFromDecodedJwt(decodedToken)

        val authentication = UsernamePasswordAuthenticationToken(user, null, user.authorities)

        SecurityContextHolder.getContext().authentication = authentication

        filterChain.doFilter(request, response)
    }

    fun userFromDecodedJwt(token: DecodedJWT): User {
        return User(
            token.subject, // id
            token.getClaim("name").asString(),
            token.getClaim("email").asString(),
            "",
            UserRole.valueOf(token.getClaim("roles").asString()),
        )
    }

    private fun recoverToken(request: HttpServletRequest): String? {
        val authHeader: String? = request.getHeader("Authorization")

        return authHeader?.replace(Regex("[Bb]earer "), "")
    }
}