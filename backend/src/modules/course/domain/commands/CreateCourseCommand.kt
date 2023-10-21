package com.davintiproject.backend.modules.course.domain.commands

import com.davintiproject.backend.common.domain.Command
import com.davintiproject.backend.modules.course.domain.entities.Course
import com.davintiproject.backend.modules.course.domain.interfaces.CourseRepository
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.stereotype.Component

data class CreateCourseDto(val name: String)

@Component
class CreateCourseCommand(
    val courseRepository: CourseRepository
) : Command<CreateCourseDto, String> {

    @PreAuthorize("hasRole('ADMIN')") // TODO: Add permissions to instructor
    override fun execute(params: CreateCourseDto): String {
        return courseRepository.save(Course(name = params.name)).id
    }
}