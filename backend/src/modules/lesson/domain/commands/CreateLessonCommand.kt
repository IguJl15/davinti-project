package com.davintiproject.backend.modules.lesson.domain.commands

import com.davintiproject.backend.common.domain.Command
import com.davintiproject.backend.modules.course.domain.queries.GetCourseById
import com.davintiproject.backend.modules.lesson.domain.entities.Lesson
import com.davintiproject.backend.modules.lesson.domain.interfaces.LessonRepository
import com.davintiproject.backend.modules.lesson.domain.queries.GetContentById
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.stereotype.Component

data class CreateLessonDto(
    val title: String,
    val mainContent: CreateContentDto,
    val supportContent: List<CreateContentDto> = emptyList(),
    val courseId: Int = 0
)

@Component
class CreateLessonCommand(
    val lessonRepository: LessonRepository,
    val getCourseById: GetCourseById,
    val createContentCommand: CreateContentCommand,
    val getContentById: GetContentById
) : Command<CreateLessonDto, Int> {

    @PreAuthorize("hasRole('INSTRUCTOR')")
    override fun execute(params: CreateLessonDto): Int {
        val mainContentId = createContentCommand.execute(params.mainContent)
        val mainContent = getContentById.execute(mainContentId)
        val course = getCourseById.execute(params.courseId)

        val supportContentIds = params.supportContent.map {
            createContentCommand.execute(it)
        }

        val supportContent = supportContentIds.map {
            getContentById.execute(it)
        }.toMutableList()

        val lesson = Lesson(
            title = params.title,
            mainContent = mainContent,
            supportContent = supportContent,
            course = course
        )

        return lessonRepository.save(lesson).id
    }
}