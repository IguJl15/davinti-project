package com.davintiproject.backend.modules.instructor.domain.queries

import com.davintiproject.backend.common.domain.Query
import com.davintiproject.backend.modules.instructor.domain.entities.Instructor
import com.davintiproject.backend.modules.instructor.domain.helpers.InstructorAuthorizationService
import com.davintiproject.backend.modules.instructor.domain.interfaces.InstructorRepository
import org.springframework.http.HttpStatus
import org.springframework.security.access.AccessDeniedException
import org.springframework.stereotype.Component
import org.springframework.web.server.ResponseStatusException

@Component
class GetInstructorById(
    val instructorRepository: InstructorRepository,
    val instructorAuthorization: InstructorAuthorizationService
) : Query<Int, Instructor> {

    // There is no need for pre-authorization because the method validates the user
    override fun execute(params: Int): Instructor {
        val instructor = instructorRepository.findById(params)
            .orElseThrow { ResponseStatusException(HttpStatus.NOT_FOUND, "Instructor not found") }

        if (!instructorAuthorization.userCanViewInstructor(instructor)) {
            throw AccessDeniedException("Você não tem permissão para acessar este recurso")
        }

        return instructor
    }
}