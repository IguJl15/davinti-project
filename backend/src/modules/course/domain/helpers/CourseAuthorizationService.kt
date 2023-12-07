package com.davintiproject.backend.modules.course.domain.helpers

import com.davintiproject.backend.modules.course.domain.entities.Course
import com.davintiproject.backend.modules.security.domain.entities.User
import com.davintiproject.backend.modules.security.domain.entities.UserRole
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Service

@Service
class CourseAuthorizationService {
    fun userCanViewCourse(course: Course): Boolean {
        if (course.isPublic) return true

        val user = SecurityContextHolder.getContext().authentication.principal as User

        return when (user.role) {
            UserRole.user -> course.studentsEnrolled.any { it.id == user.id }
            UserRole.admin -> true
            UserRole.instructor -> user.id == course.instructorId
        }
    }
}