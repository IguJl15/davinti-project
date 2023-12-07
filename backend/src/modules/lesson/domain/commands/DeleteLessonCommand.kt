package com.davintiproject.backend.modules.lesson.domain.commands

import com.davintiproject.backend.common.domain.Command
import com.davintiproject.backend.modules.instructor.domain.queries.GetInstructorById
import com.davintiproject.backend.modules.lesson.domain.interfaces.LessonRepository
import com.davintiproject.backend.modules.lesson.domain.queries.GetLessonById
import com.davintiproject.backend.modules.lesson.domain.queries.GetLessonByIdDto
import com.davintiproject.backend.modules.security.domain.entities.User
import org.springframework.http.HttpStatus
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component
import org.springframework.web.server.ResponseStatusException

@Component
class DeleteLessonCommand(
    private val getLessonById: GetLessonById,
    private val getInstructor: GetInstructorById,
    private val lessonRepository: LessonRepository
) : Command<GetLessonByIdDto, Unit> {

    @PreAuthorize("hasRole('INSTRUCTOR')")
    override fun execute(params: GetLessonByIdDto) {
        val lesson = getLessonById.execute(params)

        val user = SecurityContextHolder.getContext().authentication.principal as User
        val instructor = getInstructor.execute(user.id)

        if (instructor.courses.none { it.id == params.courseId }) {
            throw ResponseStatusException(HttpStatus.FORBIDDEN, "You don't have access to this resource")
        }

        lessonRepository.deleteById(lesson.id)
    }
}