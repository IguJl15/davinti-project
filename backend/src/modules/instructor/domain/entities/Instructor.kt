package com.davintiproject.backend.modules.instructor.domain.entities

import com.davintiproject.backend.modules.course.domain.entities.Announcement
import com.davintiproject.backend.modules.course.domain.entities.Course
import com.davintiproject.backend.modules.security.domain.entities.User
import com.davintiproject.backend.modules.security.domain.entities.UserRole
import jakarta.persistence.*

@Entity
class Instructor(
    completeName: String,
    email: String,

    @Column(nullable = false)
    val siape: String = "",

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "instructor", cascade = [CascadeType.REMOVE])
    val courses: List<Course> = emptyList(),

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "author", cascade = [CascadeType.ALL])
    val announcements: List<Announcement> = emptyList(),

    pass: String
) : User(id = 0, completeName, email, pass, UserRole.instructor, emptyList())