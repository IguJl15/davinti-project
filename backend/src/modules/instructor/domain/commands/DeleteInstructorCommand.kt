package com.davintiproject.backend.modules.instructor.domain.commands

import com.davintiproject.backend.common.domain.Command
import com.davintiproject.backend.modules.instructor.domain.interfaces.InstructorRepository
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.stereotype.Component

@Component
class DeleteInstructorCommand(
    val instructorRepository: InstructorRepository
) : Command<Int, Unit>{
    @PreAuthorize("hasRole('ADMIN')")
    override fun execute(params: Int) {
        instructorRepository.deleteById(params)
    }
}