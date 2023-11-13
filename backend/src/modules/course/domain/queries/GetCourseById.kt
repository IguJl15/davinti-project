package com.davintiproject.backend.modules.course.domain.queries

import com.davintiproject.backend.common.domain.Query
import com.davintiproject.backend.modules.course.domain.entities.Course
import com.davintiproject.backend.modules.course.domain.helpers.CourseAuthorizationService
import com.davintiproject.backend.modules.course.domain.interfaces.CourseRepository
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Component
import org.springframework.web.server.ResponseStatusException


@Component
class GetCourseById(
    val courseRepository: CourseRepository,
    val courseAuthorization: CourseAuthorizationService
) : Query<Int, Course> {

    override fun execute(params: Int): Course {
        val course = courseRepository.findById(params)
            .orElseThrow(this::notFoundException)

        if (courseAuthorization.userCanViewCourse(course)) {
            return course
        } else {
            throw notFoundException()
        }
    }

    fun notFoundException(): Exception = ResponseStatusException(HttpStatus.NOT_FOUND, "Course not found")
}