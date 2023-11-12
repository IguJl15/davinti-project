package com.davintiproject.backend.modules.instructor.domain.queries

import com.davintiproject.backend.common.domain.Query
import com.davintiproject.backend.modules.instructor.domain.entities.Instructor
import com.davintiproject.backend.modules.instructor.domain.helpers.InstructorAuthorizationService
import com.davintiproject.backend.modules.instructor.domain.interfaces.InstructorRepository
import jakarta.persistence.EntityNotFoundException
import org.springframework.security.access.AccessDeniedException
import org.springframework.stereotype.Component

@Component
class GetInstructorById(
    val instructorRepository: InstructorRepository,
    val instructorAuthorization: InstructorAuthorizationService
) : Query<Int, Instructor>{
    override fun execute(params: Int): Instructor {
        val instructor = instructorRepository.findById(params)
            .orElseThrow{EntityNotFoundException()}

        if(!instructorAuthorization.userCanViewInstructor(instructor)) {
            throw AccessDeniedException("Você não tem permissão para acessar este recurso")
        }

        return instructor
    }
}