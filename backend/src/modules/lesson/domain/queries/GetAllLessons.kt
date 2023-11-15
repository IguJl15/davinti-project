package com.davintiproject.backend.modules.lesson.domain.queries

import com.davintiproject.backend.common.domain.Query
import com.davintiproject.backend.modules.lesson.domain.entities.Lesson
import com.davintiproject.backend.modules.lesson.domain.interfaces.LessonRepository
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.stereotype.Component

@Component
class GetAllLessons(
    val lessonRepository: LessonRepository
) : Query<Int, Collection<Lesson>> {

    @PreAuthorize("hasRole('INSTRUCTOR')")
    override fun execute(params: Int): Collection<Lesson> {
        return lessonRepository.findByCourseId(params)
    }
}