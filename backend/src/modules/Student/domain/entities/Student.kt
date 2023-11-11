package com.davintiproject.backend.modules.Student.domain.entities

import com.davintiproject.backend.modules.course.domain.entities.Course
import com.davintiproject.backend.modules.security.domain.entities.User
import com.davintiproject.backend.modules.security.domain.entities.UserRole
import jakarta.persistence.Entity
import jakarta.persistence.JoinTable
import jakarta.persistence.ManyToMany
import java.time.LocalDate

@Entity
data class Student(
    override val completeName: String,
    override val email: String,
    val birthDate: LocalDate? = null,
    val registrationNumber: String = "",
    val phoneNumber: String = "",
    //TODO: val address: Address

    @ManyToMany
    @JoinTable(name = "enrollment")
    val enrolledCourses: List<Course> = emptyList(),

    override var pass: String,
) : User(completeName = completeName, email = email, role = UserRole.user, pass = pass)

