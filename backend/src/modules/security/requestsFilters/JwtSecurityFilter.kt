package com.davintiproject.backend.modules.security.requestsFilters

import com.davintiproject.backend.modules.security.services.AuthorizationService
import com.davintiproject.backend.modules.security.services.JwtService
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter

@Component
class JwtSecurityFilter(
    val jwtService: JwtService,
    val authorizationService: AuthorizationService,
) : OncePerRequestFilter() {
    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        val token = recoverToken(request)
        if (token.isNullOrBlank()) return filterChain.doFilter(request, response)


        val isTokenValid = jwtService.isTokenValid(token)
        val isAuthenticated = SecurityContextHolder.getContext().authentication?.isAuthenticated ?: false

        if (!isTokenValid || isAuthenticated) {
            return filterChain.doFilter(request, response)
        }

        val userEmail = jwtService.extractUserEmail(token)
        val userDetails = authorizationService.loadUserByUsername(userEmail)

        if (userDetails != null) {
            val authToken =
                UsernamePasswordAuthenticationToken.authenticated(userDetails, null, userDetails.authorities)

            authToken.details = WebAuthenticationDetailsSource().buildDetails(request)

            SecurityContextHolder.getContext().authentication = authToken
        }

        return filterChain.doFilter(request, response)
    }

    private fun recoverToken(request: HttpServletRequest): String? {
        val authHeader: String? = request.getHeader("Authorization")

        return authHeader?.replace(Regex("[Bb]earer "), "")
    }
}