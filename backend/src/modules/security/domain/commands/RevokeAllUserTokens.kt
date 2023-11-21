package com.davintiproject.backend.modules.security.domain.commands

import com.davintiproject.backend.common.domain.Command
import com.davintiproject.backend.modules.security.data.repositories.TokenRepository
import org.springframework.stereotype.Component

@Component
class RevokeAllUserTokens(
    private val tokenRepository: TokenRepository,
) : Command<Int, Unit> {
    override fun execute(params: Int) {
        val validUserTokens = tokenRepository.findAllValidTokenByUser(params)

        if (validUserTokens.isEmpty()) return

        tokenRepository.saveAll(validUserTokens.map { it.copy(expired = true, revoked = true) })
    }
}