package com.davintiproject.backend.modules.security.domain.commands

import com.davintiproject.backend.common.domain.Command
import com.davintiproject.backend.modules.security.domain.entities.Token
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import modules.security.data.repositories.TokenRepository
import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.web.authentication.logout.LogoutHandler
import org.springframework.stereotype.Service
import kotlin.jvm.optionals.getOrElse

data class LogoutDto(
    val request: HttpServletRequest,
    val response: HttpServletResponse,
    val authentication: Authentication
)

@Service
class LogoutCommand(
    private val tokenRepository: TokenRepository
) : Command<LogoutDto, Unit>, LogoutHandler {

    override fun execute(params: LogoutDto) {
        return logout(
            params.request,
            params.response,
            params.authentication
        )
    }

    override fun logout(
        request: HttpServletRequest,
        response: HttpServletResponse,
        authentication: Authentication
    ) {
        val authHeader = request.getHeader("Authorization")

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return
        }

        val jwt = authHeader.substring(7)

        val storedToken: Token = tokenRepository.findByToken(jwt).getOrElse { return }

        tokenRepository.save(
            storedToken.copy(expired = true, revoked = true)
        )

        SecurityContextHolder.clearContext()
    }
}

