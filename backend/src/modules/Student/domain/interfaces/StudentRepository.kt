package com.davintiproject.backend.modules.Student.domain.interfaces

import com.davintiproject.backend.modules.Student.domain.entities.Student
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface StudentRepository : JpaRepository<Student, Int> {
    fun findByEmail(email: String): Optional<Student>
}