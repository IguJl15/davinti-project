package com.davintiproject.backend.modules.lesson.domain.views

import com.davintiproject.backend.modules.lesson.domain.entities.Content
import com.davintiproject.backend.modules.lesson.domain.entities.Lesson

data class LessonView(
    val id: Int,

    val title: String,
    val mainContent: Content,
    val supportContent: List<Content>,
) {
    companion object {
        fun fromLesson(lesson: Lesson): LessonView {
            return LessonView(lesson.id, lesson.title, lesson.mainContent, lesson.supportContent)
        }
    }
}