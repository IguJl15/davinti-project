package com.davintiproject.backend.modules.security.domain.commands

import com.davintiproject.backend.common.domain.Command
import com.davintiproject.backend.modules.security.domain.entities.Token
import com.davintiproject.backend.modules.security.domain.entities.TokenType
import com.davintiproject.backend.modules.security.domain.entities.User
import modules.security.data.repositories.TokenRepository

data class SaveTokenDto(
    val user: User,
    val token: String
)

class SaveTokenCommand(
    val tokenRepository: TokenRepository
) : Command<SaveTokenDto, Int> {
    override fun execute(params: SaveTokenDto): Int {
        var token = Token(
            user = params.user,
            token = params.token,
            tokenType = TokenType.BEARER,
            expired = false,
            revoked = false,
        )

        val savedToken = tokenRepository.save(token)

        return savedToken.id;
    }
}