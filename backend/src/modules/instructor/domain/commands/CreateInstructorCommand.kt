package com.davintiproject.backend.modules.instructor.domain.commands

import com.davintiproject.backend.common.domain.Command
import com.davintiproject.backend.modules.instructor.domain.entities.Instructor
import com.davintiproject.backend.modules.instructor.domain.interfaces.InstructorRepository
import org.springframework.http.HttpStatus
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Component
import org.springframework.web.server.ResponseStatusException
import kotlin.jvm.optionals.getOrNull

data class CreateInstructorDto(
    val name: String,
    val email: String,
    val pass: String,
    val siape: String
)

@Component
class CreateInstructorCommand(
    val instructorRepository: InstructorRepository
) : Command<CreateInstructorDto, Int>{

    @PreAuthorize("hasRole('ADMIN')")
    override fun execute(params: CreateInstructorDto): Int {
        if(instructorRepository.findByEmail(params.email).getOrNull() != null) {
            throw ResponseStatusException(HttpStatus.BAD_REQUEST, "Email already in use")
        }

        val encryptedPassword = BCryptPasswordEncoder().encode(params.pass)

        val instructor = instructorRepository.save(Instructor(
            completeName = params.name,
            email = params.email,
            siape = params.siape,
            pass = encryptedPassword
        ))

        return instructor.id
    }
}