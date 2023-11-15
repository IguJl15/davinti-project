package com.davintiproject.backend.modules.lesson.domain.entities

import com.davintiproject.backend.modules.course.domain.entities.Course
import jakarta.persistence.*

@Entity
class Lesson(
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    val id: Int =0,

    @ManyToOne
    val mainContent: Content,

    @OneToMany
    val supportContent: List<Content>,

    @ManyToOne(fetch = FetchType.LAZY, cascade = [CascadeType.ALL] ,optional = false)
    @JoinColumn(name = "course_id")
    val course: Course
)