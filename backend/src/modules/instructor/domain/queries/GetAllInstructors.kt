package com.davintiproject.backend.modules.instructor.domain.queries

import com.davintiproject.backend.common.domain.Query
import com.davintiproject.backend.modules.instructor.domain.entities.Instructor
import com.davintiproject.backend.modules.instructor.domain.interfaces.InstructorRepository
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.stereotype.Component

@Component
class GetAllInstructors(
    val instructorRepository: InstructorRepository
) : Query<Unit, Collection<Instructor>>{

    @PreAuthorize("hasRole('ADMIN')")
    override fun execute(params: Unit): Collection<Instructor> {
        return instructorRepository.findAll()
    }

}