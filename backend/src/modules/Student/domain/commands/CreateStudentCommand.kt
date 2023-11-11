package com.davintiproject.backend.modules.Student.domain.commands

import com.davintiproject.backend.common.domain.Command
import com.davintiproject.backend.modules.Student.domain.entities.Student
import com.davintiproject.backend.modules.Student.domain.interfaces.StudentRepository
import org.springframework.http.HttpStatus
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Component
import org.springframework.web.server.ResponseStatusException
import java.time.LocalDate
import kotlin.jvm.optionals.getOrNull

data class CreateStudentDto(
    val name: String,
    val birthDate: LocalDate,
    val registrationNumber: String,
    val email: String,
    val phoneNumber: String,
    val password: String,
    )

@Component
class CreateStudentCommand(
    val studentRepository: StudentRepository,
) : Command<CreateStudentDto, Int> {
    @PreAuthorize("isAnonymous()")
    override fun execute(params: CreateStudentDto): Int {
        if (studentRepository.findByEmail(params.email).getOrNull() != null) {
            throw ResponseStatusException(HttpStatus.BAD_REQUEST, "Email already in use")
        }

        val encryptedPassword = BCryptPasswordEncoder().encode(params.password)

        val savedStudent = studentRepository.save(
            Student(
            completeName = params.name,
                birthDate = params.birthDate,
            registrationNumber = params.registrationNumber,
            email = params.email,
                phoneNumber = params.phoneNumber,
                pass = encryptedPassword
            )
        )

        return savedStudent.id
    }
}