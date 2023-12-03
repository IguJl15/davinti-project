package com.davintiproject.backend.modules.course.domain.queries

import com.davintiproject.backend.common.domain.Query
import com.davintiproject.backend.modules.Student.domain.entities.Student
import com.davintiproject.backend.modules.course.domain.entities.Announcement
import com.davintiproject.backend.modules.course.domain.entities.Course
import com.davintiproject.backend.modules.course.domain.helpers.CourseAuthorizationService
import com.davintiproject.backend.modules.course.domain.interfaces.CourseRepository
import com.davintiproject.backend.modules.instructor.domain.entities.Instructor
import com.davintiproject.backend.modules.lesson.domain.entities.Lesson
import com.fasterxml.jackson.annotation.JsonIgnore
import jakarta.persistence.*
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Component
import org.springframework.web.server.ResponseStatusException

data class InstructorView(
    val name: String,
    val siape: String,
)

data class CourseView(
    val id: Int,
    var name: String,
    val description: String?,

    val instructor: InstructorView,

    val lessons: List<Lesson>,

    @JsonIgnore
    val course: Course
) {
    companion object {

        fun fromCourse(course: Course): CourseView {
            val instructor = InstructorView(course.instructor.completeName, course.instructor.siape)

            return CourseView(course.id, course.name, course.description, instructor, course.lessons, course)

        }
    }
}

@Component
class GetCourseById(
    val courseRepository: CourseRepository,
    val courseAuthorization: CourseAuthorizationService
) : Query<Int, CourseView> {

    override fun execute(params: Int): CourseView {
        val course = courseRepository.findById(params)
            .orElseThrow(this::notFoundException)

        if (courseAuthorization.userCanViewCourse(course)) {
            return CourseView.fromCourse(course)
        } else {
            throw notFoundException()
        }
    }

    fun notFoundException(): Exception = ResponseStatusException(HttpStatus.NOT_FOUND, "Course not found")
}