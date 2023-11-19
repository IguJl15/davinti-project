package com.davintiproject.backend.modules.lesson.domain.queries

import com.davintiproject.backend.common.domain.Query
import com.davintiproject.backend.modules.lesson.domain.interfaces.LessonRepository
import com.davintiproject.backend.modules.lesson.domain.views.LessonView
import org.springframework.stereotype.Component

data class AllCourseLessonsView(
    val lessons: List<LessonView>
)


@Component
class GetAllLessons(
    val lessonRepository: LessonRepository
) : Query<Int, AllCourseLessonsView> {
    override fun execute(params: Int): AllCourseLessonsView {
        val lessons = lessonRepository.findByCourseId(params)

        return AllCourseLessonsView(lessons = lessons.map { LessonView.fromLesson(it) })
    }
}