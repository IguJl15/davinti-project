package com.davintiproject.backend.modules.course.domain.entities

import com.davintiproject.backend.modules.Student.domain.entities.Student
import jakarta.persistence.*

@Entity
data class Course(
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    val id: String = "",
    val name: String = "",
    val isPublic: Boolean = false,

    @ManyToMany(mappedBy = "enrolledCourses")
    val studentsEnrolled: List<Student> = emptyList(),
)