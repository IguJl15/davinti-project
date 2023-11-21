package com.davintiproject.backend.modules.course.domain.entities

import com.davintiproject.backend.modules.instructor.domain.entities.Instructor
import com.fasterxml.jackson.annotation.JsonIgnore
import jakarta.persistence.*
import jakarta.validation.constraints.NotBlank
import java.time.LocalDateTime

@Entity
class Announcement (
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    val id: Int = 0,

    @NotBlank
    val title: String,

    @NotBlank
    val content: String,

    val createdAt: LocalDateTime = LocalDateTime.now(),

    @ManyToOne(fetch = FetchType.LAZY, cascade = [CascadeType.ALL], optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    @JsonIgnore
    val author: Instructor,

    @ManyToOne(fetch = FetchType.LAZY, cascade = [CascadeType.ALL], optional = false)
    @JoinColumn(name = "course_id")
    @JsonIgnore
    val course: Course
)