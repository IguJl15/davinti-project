package com.davintiproject.backend.modules.lesson.domain.queries

import com.davintiproject.backend.common.domain.Query
import com.davintiproject.backend.modules.lesson.domain.entities.Lesson
import com.davintiproject.backend.modules.lesson.domain.interfaces.LessonRepository
import org.springframework.http.HttpStatus
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.stereotype.Component
import org.springframework.web.server.ResponseStatusException

data class GetLessonByIdDto (
    val courseId: Int,
    val lessonId: Int
)

@Component
class GetLessonById (
    val lessonRepository: LessonRepository
) : Query<GetLessonByIdDto, Lesson>{
    @PreAuthorize("hasRole('INSTRUCTOR')")
    override fun execute(params: GetLessonByIdDto): Lesson {
        val lesson = lessonRepository.findByIdAndCourseId(params.lessonId, params.courseId)
            .orElseThrow { ResponseStatusException(HttpStatus.NOT_FOUND) }

        return lesson
    }
}