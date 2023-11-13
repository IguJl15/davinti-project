package com.davintiproject.backend.modules.course.domain.entities

import com.davintiproject.backend.modules.Student.domain.entities.Student
import com.davintiproject.backend.modules.instructor.domain.entities.Instructor
import jakarta.persistence.*

@Entity
data class Course(
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    val id: Int = 0,
    val name: String = "",
    val isPublic: Boolean = false,

    @ManyToMany(mappedBy = "enrolledCourses")
    val studentsEnrolled: List<Student> = emptyList(),

    @ManyToOne(cascade = [CascadeType.ALL])
    @JoinColumn(name = "user_id")
    val instructor: Instructor? = null,
)