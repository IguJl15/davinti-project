package com.davintiproject.backend.modules.Student.domain.entities

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import java.time.LocalDate

@Entity
data class Student (
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    val id: String = "",
    val name: String = "",
    val dateOfBirth: LocalDate? = null,
    val registrationNumber: String = "",
    val email: String = "",
    val phoneNumber: String = ""
    //TODO: val address: Address
)