package com.davintiproject.backend.modules.security.domain.commands

import com.davintiproject.backend.common.domain.Command
import com.davintiproject.backend.modules.security.data.repositories.TokenRepository
import com.davintiproject.backend.modules.security.domain.entities.Token
import com.davintiproject.backend.modules.security.domain.entities.TokenType
import com.davintiproject.backend.modules.security.domain.entities.User
import org.springframework.stereotype.Component

data class SaveTokenDto(
    val user: User, val token: String
)

@Component
class SaveTokenCommand(
    val tokenRepository: TokenRepository
) : Command<SaveTokenDto, Int> {
    override fun execute(params: SaveTokenDto): Int {
        val token = Token(
            user = params.user,
            token = params.token,
            tokenType = TokenType.BEARER,
            expired = false,
            revoked = false,
        )

        val savedToken = tokenRepository.save(token)

        return savedToken.id
    }
}