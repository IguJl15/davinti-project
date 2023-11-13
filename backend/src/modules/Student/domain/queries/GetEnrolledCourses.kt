package com.davintiproject.backend.modules.Student.domain.queries

import com.davintiproject.backend.common.domain.Query
import com.davintiproject.backend.modules.Student.domain.entities.Student
import com.davintiproject.backend.modules.course.domain.entities.Course
import com.davintiproject.backend.modules.course.domain.helpers.CourseAuthorizationService
import com.davintiproject.backend.modules.security.domain.entities.User
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component

@Component
class GetEnrolledCourses(
    val courseAuthorizationService: CourseAuthorizationService
) : Query<String, List<Course>> {
    @PreAuthorize("hasRole('ADMIN') or authentication.principal.id == #params")
    override fun execute(params: String): List<Course> {
        val user = SecurityContextHolder.getContext().authentication.principal as User

        if (user !is Student) {
            return emptyList()
        }

        return user.enrolledCourses.filter(courseAuthorizationService::userCanViewCourse)


    }
}