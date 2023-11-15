package com.davintiproject.backend.modules.lesson.domain.commands

import com.davintiproject.backend.common.domain.Command
import com.davintiproject.backend.modules.lesson.domain.entities.Content
import com.davintiproject.backend.modules.lesson.domain.entities.DocumentContent
import com.davintiproject.backend.modules.lesson.domain.entities.UrlContent
import com.davintiproject.backend.modules.lesson.domain.entities.VideoContent
import com.davintiproject.backend.modules.lesson.domain.interfaces.ContentRepository
import org.springframework.http.HttpStatus
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.stereotype.Component
import org.springframework.web.server.ResponseStatusException

data class CreateContentDto(
    val title: String,
    val url: String,
    val contentType: String
)

@Component
class CreateContentCommand(
    val contentRepository: ContentRepository
) : Command<CreateContentDto, Int>{

    @PreAuthorize("hasRole('INSTRUCTOR')")
    override fun execute(params: CreateContentDto): Int {
        val content: Content = when (params.contentType) {
            "document" -> DocumentContent(title = params.title, filePath = params.url)
            "url" -> UrlContent(title = params.title, url = params.url)
            "video" -> VideoContent(title = params.title, videoUrl = params.url)
            else -> throw ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid content type")
        }

        return contentRepository.save(content).id
    }
}