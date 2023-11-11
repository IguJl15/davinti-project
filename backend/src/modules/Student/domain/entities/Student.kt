package com.davintiproject.backend.modules.Student.domain.entities

import com.davintiproject.backend.modules.course.domain.entities.Course
import com.davintiproject.backend.modules.security.domain.entities.User
import com.davintiproject.backend.modules.security.domain.entities.UserRole
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.JoinTable
import jakarta.persistence.ManyToMany
import java.time.LocalDate

@Entity
class Student(
    completeName: String,
    email: String,


    @Column()
    val birthDate: LocalDate? = null,

    @Column(nullable = false)
    val registrationNumber: String = "",

    @Column(nullable = false)
    val phoneNumber: String = "",

    @ManyToMany
    @JoinTable(name = "enrollment")
    val enrolledCourses: List<Course> = emptyList(),

    pass: String,
) : User(0, completeName, email, pass, UserRole.user, emptyList())

