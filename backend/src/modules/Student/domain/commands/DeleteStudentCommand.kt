package com.davintiproject.backend.modules.Student.domain.commands

import com.davintiproject.backend.common.domain.Command
import com.davintiproject.backend.modules.Student.domain.interfaces.StudentRepository
import com.davintiproject.backend.modules.Student.domain.queries.GetStudentById
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.stereotype.Component

@Component
class DeleteStudentCommand(
    val studentRepository: StudentRepository
) : Command<Int, Unit> {

    @PreAuthorize("hasRole('ADMIN')")
    override fun execute(params: Int) {
        studentRepository.deleteById(params)
    }
}