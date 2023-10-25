package com.davintiproject.backend.modules.course.domain.entities

import com.davintiproject.backend.data.entities.User
import jakarta.persistence.*

@Entity
data class Course(
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    val id: String = "",
    val name: String = "",
    val isPublic: Boolean = false,

    @ManyToMany
    val studentsEnrolled: List<User> = emptyList(),
)