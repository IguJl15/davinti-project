package com.davintiproject.backend.modules.course.domain.helpers

import com.davintiproject.backend.data.entities.User
import com.davintiproject.backend.data.entities.UserRole
import com.davintiproject.backend.modules.course.domain.entities.Course
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Service

@Service
class CourseAuthorizationService {
    fun userCanViewCouse(course: Course): Boolean {
        // TODO: if (couse.isPublic) return true

        val user = SecurityContextHolder.getContext().authentication.principal as User

        if (user.role == UserRole.admin) return true

        // TODO: if ( user.role == UserRole.instructor)  if (user.id == course.instructor.id) return true else continue

        if (user.role == UserRole.user) {
            return course.studentsEnrolled.any { it.id == user.id }
        }


        return false
    }
}