package com.davintiproject.backend.modules.lesson.domain.commands

import com.davintiproject.backend.common.domain.Command
import com.davintiproject.backend.main
import com.davintiproject.backend.modules.course.domain.interfaces.CourseRepository
import com.davintiproject.backend.modules.course.domain.queries.GetCourseById
import com.davintiproject.backend.modules.lesson.domain.entities.Content
import com.davintiproject.backend.modules.lesson.domain.entities.Lesson
import com.davintiproject.backend.modules.lesson.domain.interfaces.LessonRepository
import com.davintiproject.backend.modules.lesson.domain.queries.GetContentById
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.stereotype.Component

data class CreateLessonDto(
    val mainContent: CreateContentDto,
    val supportContent: List<CreateContentDto>?,
    val courseId: Int
)

@Component
class CreateLessonCommand(
    val lessonRepository: LessonRepository,
    val getCourseById: GetCourseById,
    val createContentCommand: CreateContentCommand,
    val getContentById: GetContentById
) : Command<CreateLessonDto, Int>{

    @PreAuthorize("hasRole('INSTRUCTOR')")
    override fun execute(params: CreateLessonDto): Int {
        val mainContentId = createContentCommand.execute(params.mainContent)
        val mainContent = getContentById.execute(mainContentId)
        val course = getCourseById.execute(params.courseId)

        val supportContentIds = params.supportContent?.map {
            createContentCommand.execute(it)
        } ?: emptyList()

        val supportContent = supportContentIds.map {
            getContentById.execute(it)
        }

        val lesson = Lesson(
            mainContent = mainContent,
            supportContent = supportContent,
            course = course
        )

        return lessonRepository.save(lesson).id
    }
}