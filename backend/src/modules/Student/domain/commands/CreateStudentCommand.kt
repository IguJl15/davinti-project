package com.davintiproject.backend.modules.Student.domain.commands

import com.davintiproject.backend.common.domain.Command
import com.davintiproject.backend.modules.Student.domain.entities.Student
import com.davintiproject.backend.modules.Student.domain.interfaces.StudentRepository
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.stereotype.Component
import java.time.LocalDate

data class CreateStudentDto(
    val name: String,
    val dateOfBirth: LocalDate,
    val registrationNumber: String,
    val email: String,
    val phoneNumber: String,
    )

@Component
class CreateStudentCommand(
    val studentRepository: StudentRepository
) : Command<CreateStudentDto, Int> {
    @PreAuthorize("hasRole('ADMIN')") // TODO: Add permissions to instructor
    override fun execute(params: CreateStudentDto): Int {
        return studentRepository.save(Student(
            completeName = params.name,
            dateOfBirth = params.dateOfBirth,
            registrationNumber = params.registrationNumber,
            email = params.email,
            phoneNumber = params.phoneNumber
        )).id
    }
}