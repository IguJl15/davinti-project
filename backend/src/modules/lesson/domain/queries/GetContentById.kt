package com.davintiproject.backend.modules.lesson.domain.queries

import com.davintiproject.backend.common.domain.Query
import com.davintiproject.backend.modules.lesson.domain.entities.Content
import com.davintiproject.backend.modules.lesson.domain.interfaces.ContentRepository
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Component
import org.springframework.web.server.ResponseStatusException

/**
 * TODO: Add authorization if this query gonna be used as direct access to content.
 *    It only gonna be needed if we create a endpoint that access the content directly.
 *    While this query is only used by another commands/queries that verify authorization
 *    by calling the `CourseAuthorizationService` class
 */
@Component
class GetContentById(
    val contentRepository: ContentRepository
) : Query<Int, Content> {
    override fun execute(params: Int): Content {
        return contentRepository.findById(params)
            .orElseThrow { ResponseStatusException(HttpStatus.NOT_FOUND) }
    }
}