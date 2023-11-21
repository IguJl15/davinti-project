package com.davintiproject.backend.modules.course.domain.entities

import com.davintiproject.backend.modules.Student.domain.entities.Student
import com.davintiproject.backend.modules.instructor.domain.entities.Instructor
import com.davintiproject.backend.modules.lesson.domain.entities.Lesson
import com.fasterxml.jackson.annotation.JsonIgnore
import jakarta.persistence.*

@Entity
class Course(
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    val id: Int = 0,
    var name: String = "",
    var isPublic: Boolean = false,

    @ManyToMany(mappedBy = "enrolledCourses")
    @JsonIgnore
    val studentsEnrolled: MutableList<Student> = mutableListOf(),

    @ManyToOne(fetch = FetchType.LAZY, cascade = [CascadeType.ALL], optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    @JsonIgnore
    val instructor: Instructor,

    @OneToMany(mappedBy = "course")
    val lessons: List<Lesson> = emptyList()
) {
    val instructorId get() = instructor.id
}