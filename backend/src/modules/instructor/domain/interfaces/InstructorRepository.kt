package com.davintiproject.backend.modules.instructor.domain.interfaces

import com.davintiproject.backend.modules.Student.domain.entities.Student
import com.davintiproject.backend.modules.instructor.domain.entities.Instructor
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface InstructorRepository : JpaRepository<Instructor, Int> {
    fun findByEmail(email: String): Optional<Instructor>
}