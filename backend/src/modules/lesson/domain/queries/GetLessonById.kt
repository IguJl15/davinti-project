package com.davintiproject.backend.modules.lesson.domain.queries

import com.davintiproject.backend.common.domain.Query
import com.davintiproject.backend.modules.course.domain.queries.GetCourseById
import com.davintiproject.backend.modules.lesson.domain.views.LessonView
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Component
import org.springframework.web.server.ResponseStatusException

data class GetLessonByIdDto (
    val courseId: Int,
    val lessonId: Int
)

@Component
class GetLessonById (
    private val getCourse: GetCourseById
) : Query<GetLessonByIdDto, LessonView> {
    override fun execute(params: GetLessonByIdDto): LessonView {
        val course = getCourse.execute(params.courseId)

        val lesson = course.lessons.singleOrNull { it.id == params.lessonId }
            ?: throw ResponseStatusException(HttpStatus.NOT_FOUND)

        return LessonView.fromLesson(lesson)
    }
}