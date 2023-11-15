package com.davintiproject.backend.modules.lesson.domain.queries

import com.davintiproject.backend.common.domain.Query
import com.davintiproject.backend.modules.lesson.domain.entities.Content
import com.davintiproject.backend.modules.lesson.domain.interfaces.ContentRepository
import org.springframework.http.HttpStatus
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.stereotype.Component
import org.springframework.web.server.ResponseStatusException

@Component
class GetContentById(
    val contentRepository: ContentRepository
) : Query<Int, Content> {

    @PreAuthorize("hasRole('INSTRUCTOR')")
    override fun execute(params: Int): Content {
        val content = contentRepository.findById(params)
            .orElseThrow { ResponseStatusException(HttpStatus.NOT_FOUND)}

        return content
    }

}