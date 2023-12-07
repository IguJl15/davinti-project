package com.davintiproject.backend.modules.Student.domain.queries

import com.davintiproject.backend.common.domain.Query
import com.davintiproject.backend.modules.Student.domain.entities.Student
import com.davintiproject.backend.modules.Student.domain.helpers.StudentAuthorizationService
import com.davintiproject.backend.modules.Student.domain.interfaces.StudentRepository
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.stereotype.Component

@Component
class GetAllStudents(
    val studentRepository: StudentRepository,
    val studentAuthorization: StudentAuthorizationService
) : Query<Unit, Collection<Student>> {
    @PreAuthorize("hasRole('ADMIN')")
    override fun execute(params: Unit): List<Student> {
        val list = studentRepository.findAll()

        return list.filter(studentAuthorization::userCanViewStudent)
    }
}