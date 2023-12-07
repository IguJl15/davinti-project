package com.davintiproject.backend.modules.lesson.domain.commands

import com.davintiproject.backend.common.domain.Command
import com.davintiproject.backend.modules.lesson.domain.entities.*
import com.davintiproject.backend.modules.lesson.domain.interfaces.ContentRepository
import org.springframework.http.HttpStatus
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.stereotype.Component
import org.springframework.web.server.ResponseStatusException

data class CreateContentDto(
    val title: String,
    val url: String,
    val text: String?,
    val contentType: String
)

@Component
class CreateContentCommand(
    private val contentRepository: ContentRepository,
) : Command<CreateContentDto, Int> {

    @PreAuthorize("hasRole('INSTRUCTOR')")
    override fun execute(params: CreateContentDto): Int {
        val content: Content = when (params.contentType) {
            "text" -> TextContent(title = params.title, text = params.text!!)
            "document" -> DocumentContent(title = params.title, filePath = params.url)
            "url" -> UrlContent(title = params.title, url = params.url)
            "video" -> VideoContent(title = params.title, videoUrl = params.url)
            else -> throw ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid content type")
        }

        return contentRepository.save(content).id
    }
}