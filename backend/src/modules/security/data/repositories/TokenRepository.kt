package com.davintiproject.backend.modules.security.data.repositories

import com.davintiproject.backend.modules.security.domain.entities.Token
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import java.util.*
import org.springframework.stereotype.Repository as RepositoryAnnotation

@RepositoryAnnotation
interface TokenRepository : CrudRepository<Token, Int> {
    @Query(
        value = """
            select t from Token t inner join users u 
            on t.user.id = u.id  
            where u.id = :id and (t.expired = false or t.revoked = false)  
            """
    )
    fun findAllValidTokenByUser(id: Int): List<Token>
    fun findByToken(token: String): Optional<Token>
}
