package com.davintiproject.backend.modules.Student.domain.queries

import com.davintiproject.backend.common.domain.Query
import com.davintiproject.backend.modules.Student.domain.entities.Student
import com.davintiproject.backend.modules.Student.domain.helpers.StudentAuthorizationService
import com.davintiproject.backend.modules.Student.domain.interfaces.StudentRepository
import jakarta.persistence.EntityNotFoundException
import org.springframework.security.access.AccessDeniedException
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.stereotype.Component

@Component
class GetStudentById(
    val studentRepository: StudentRepository,
    val studentAuthorization: StudentAuthorizationService
) : Query<Int, Student> {

    @PreAuthorize("hasRole('ADMIN') or authentication.principal.id == #params")
    override fun execute(params: Int): Student {
        val student = studentRepository.findById(params)
            .orElseThrow { EntityNotFoundException() }

        if (studentAuthorization.userCanViewStudent(student)) {
            return student
        } else {
            throw AccessDeniedException("Você não tem permissão para acessar este recurso")
        }
    }
}