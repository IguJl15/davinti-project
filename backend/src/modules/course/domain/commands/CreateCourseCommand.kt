package com.davintiproject.backend.modules.course.domain.commands

import com.davintiproject.backend.common.domain.Command
import com.davintiproject.backend.modules.course.domain.entities.Course
import com.davintiproject.backend.modules.course.domain.interfaces.CourseRepository
import com.davintiproject.backend.modules.instructor.domain.interfaces.InstructorRepository
import com.davintiproject.backend.modules.security.domain.entities.User
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component

data class CreateCourseDto(val name: String)

@Component
class CreateCourseCommand(
    val courseRepository: CourseRepository,
    val instructorRepository: InstructorRepository
) : Command<CreateCourseDto, Int> {

    @PreAuthorize("hasRole('INSTRUCTOR')")
    override fun execute(params: CreateCourseDto): Int {
        val instructorUser = SecurityContextHolder.getContext().authentication.principal as User

        val instructor = instructorRepository.findById(instructorUser.id).get()

        val course = courseRepository.save(
            Course(name = params.name, isPublic = true, instructor = instructor)
        )

        return course.id
    }
}