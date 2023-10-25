package com.davintiproject.backend.modules.Student.domain.interfaces

import com.davintiproject.backend.modules.Student.domain.entities.Student
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface StudentRepository : JpaRepository<Student, String> {
}