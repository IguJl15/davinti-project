package com.davintiproject.backend.modules.lesson.domain.interfaces

import com.davintiproject.backend.modules.lesson.domain.entities.Lesson
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.Optional

@Repository
interface LessonRepository : JpaRepository<Lesson, Int> {
    fun findByCourseId(courseId: Int): Collection<Lesson>
    fun findByIdAndCourseId(lessonId: Int, courseId: Int): Optional<Lesson>
}