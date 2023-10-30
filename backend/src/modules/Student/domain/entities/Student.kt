package com.davintiproject.backend.modules.Student.domain.entities

import com.davintiproject.backend.modules.course.domain.entities.Course
import com.davintiproject.backend.modules.security.data.entities.User
import jakarta.persistence.Entity
import jakarta.persistence.JoinTable
import jakarta.persistence.ManyToMany
import java.time.LocalDate

@Entity
data class Student(
    override val completeName: String,
    override val email: String,
    val dateOfBirth: LocalDate? = null,
    val registrationNumber: String = "",
    val phoneNumber: String = "",
    //TODO: val address: Address

    @ManyToMany
    @JoinTable(name = "enrollment")
    val enrolledCourses: List<Course> = emptyList()
) : User(completeName = completeName, email = email)

