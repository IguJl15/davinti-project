package com.davintiproject.backend.modules.Student.domain.helpers

import com.davintiproject.backend.data.entities.User
import com.davintiproject.backend.data.entities.UserRole
import com.davintiproject.backend.modules.Student.domain.entities.Student
import com.davintiproject.backend.modules.Student.domain.interfaces.StudentRepository
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Service

@Service
class StudentAuthorizationService {
    fun userCanViewStudent(student: Student): Boolean {
        val user = SecurityContextHolder.getContext().authentication.principal as User

        if (user.role == UserRole.admin) return true

        if(user.role == UserRole.user) {
            if(user.id == student.id) return true
        }

        return false
    }
}