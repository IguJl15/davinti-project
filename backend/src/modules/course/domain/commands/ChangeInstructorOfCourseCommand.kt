package com.davintiproject.backend.modules.course.domain.commands

import com.davintiproject.backend.common.domain.Command
import com.davintiproject.backend.modules.course.domain.entities.Course
import com.davintiproject.backend.modules.course.domain.interfaces.CourseRepository
import com.davintiproject.backend.modules.course.domain.queries.CourseView
import com.davintiproject.backend.modules.course.domain.queries.GetCourseById
import com.davintiproject.backend.modules.instructor.domain.queries.GetInstructorById
import org.springframework.http.HttpStatus
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.stereotype.Component
import org.springframework.web.server.ResponseStatusException

data class ChangeInstructorOfCourseDto(
    val courseId: Int,
    val instructorId: Int
)

@Component
class ChangeInstructorOfCourseCommand(
    val courseRepository: CourseRepository,
    val getInstructorById: GetInstructorById
) : Command<ChangeInstructorOfCourseDto, CourseView>{
    @PreAuthorize("hasRole('ADMIN')")
    override fun execute(params: ChangeInstructorOfCourseDto): CourseView {
        val course = courseRepository.findById(params.courseId).orElseThrow {
            ResponseStatusException(HttpStatus.NOT_FOUND)
        }
        val instructor = getInstructorById.execute(params.instructorId)

        val updatedCourse = Course(
            id = course.id,
            name = course.name,
            isPublic = course.isPublic,
            description = course.description,
            studentsEnrolled = course.studentsEnrolled,
            instructor = instructor,
            lessons = course.lessons,
            announcements = course.announcements
        )

        courseRepository.save(updatedCourse)

        return CourseView.fromCourse(updatedCourse)
    }
}