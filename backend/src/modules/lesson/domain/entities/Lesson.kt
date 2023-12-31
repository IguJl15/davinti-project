package com.davintiproject.backend.modules.lesson.domain.entities

import com.davintiproject.backend.modules.course.domain.entities.Course
import com.fasterxml.jackson.annotation.JsonIgnore
import jakarta.persistence.*
import jakarta.validation.constraints.NotBlank

@Entity
class Lesson(
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    val id: Int = 0,

    @NotBlank
    val title: String,

    @OneToOne(cascade = [CascadeType.ALL], orphanRemoval = true)
    @JoinColumn
    val mainContent: Content,

    @OneToMany(cascade = [CascadeType.ALL], orphanRemoval = true)
    val supportContent: MutableList<Content>,

    @ManyToOne(fetch = FetchType.LAZY, cascade = [CascadeType.PERSIST, CascadeType.MERGE], optional = false)
    @JoinColumn(name = "course_id")
    @JsonIgnore
    val course: Course
)