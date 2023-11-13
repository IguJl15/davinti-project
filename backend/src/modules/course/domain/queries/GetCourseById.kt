package com.davintiproject.backend.modules.course.domain.queries

import com.davintiproject.backend.common.domain.Query
import com.davintiproject.backend.modules.course.domain.entities.Course
import com.davintiproject.backend.modules.course.domain.helpers.CourseAuthorizationService
import com.davintiproject.backend.modules.course.domain.interfaces.CourseRepository
import jakarta.persistence.EntityNotFoundException
import org.springframework.security.access.AccessDeniedException
import org.springframework.stereotype.Component


@Component
class GetCourseById(
    val courseRepository: CourseRepository,
    val courseAuthorization: CourseAuthorizationService
) : Query<Int, Course> {

    override fun execute(params: Int): Course {
        val course = courseRepository.findById(params)
            .orElseThrow { EntityNotFoundException() }

        if (courseAuthorization.userCanViewCourse(course)) {
            return course
        } else {
            throw AccessDeniedException("Você não tem permissão para acessar este recurso")
        }
    }
}