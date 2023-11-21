package com.davintiproject.backend.modules.instructor.domain.helpers

import com.davintiproject.backend.modules.instructor.domain.entities.Instructor
import com.davintiproject.backend.modules.security.domain.entities.User
import com.davintiproject.backend.modules.security.domain.entities.UserRole
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Service

@Service
class InstructorAuthorizationService {
    fun userCanViewInstructor(instructor: Instructor): Boolean {
        val user = SecurityContextHolder.getContext().authentication.principal as User

        if (user.role == UserRole.admin) return true

        if (user.role == UserRole.instructor) {
            if (user.id == instructor.id) return true
        }

        return false
    }
}