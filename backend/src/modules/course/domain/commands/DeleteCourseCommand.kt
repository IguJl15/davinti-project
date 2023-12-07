package com.davintiproject.backend.modules.course.domain.commands

import com.davintiproject.backend.common.domain.Command
import com.davintiproject.backend.modules.course.domain.entities.Course
import com.davintiproject.backend.modules.course.domain.interfaces.CourseRepository
import com.davintiproject.backend.modules.course.domain.queries.GetCourseById
import com.davintiproject.backend.modules.instructor.domain.queries.GetInstructorById
import com.davintiproject.backend.modules.security.domain.entities.User
import org.springframework.http.HttpStatus
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.security.core.context.SecurityContextHolder

import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.server.ResponseStatusException

@Component
class DeleteCourseCommand(
    private val getCourseById: GetCourseById,
    private val courseRepository: CourseRepository,
    private val getInstructor: GetInstructorById
) : Command<Int, Unit>{
    @PreAuthorize("hasRole('INSTRUCTOR')")
    @Transactional
    override fun execute(params: Int) {
        val course = getCourseById.execute(params)

        val user = SecurityContextHolder.getContext().authentication.principal as User
        val instructor = getInstructor.execute(user.id)

        if (instructor.courses.none { it.id == params }) {
            throw ResponseStatusException(HttpStatus.FORBIDDEN, "You don't have access to this resource")
        }

        courseRepository.deleteById(course.id)
    }

}