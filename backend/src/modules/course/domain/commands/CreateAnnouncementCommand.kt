package com.davintiproject.backend.modules.course.domain.commands

import com.davintiproject.backend.common.domain.Command
import com.davintiproject.backend.modules.course.domain.entities.Announcement
import com.davintiproject.backend.modules.course.domain.interfaces.AnnouncementRepository
import com.davintiproject.backend.modules.course.domain.queries.GetCourseById
import com.davintiproject.backend.modules.instructor.domain.queries.GetInstructorById
import com.davintiproject.backend.modules.security.domain.entities.User
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component

data class CreateAnnouncementDto(
    val title: String,
    val content: String,
    val courseId: Int = 0
)

@Component
class CreateAnnouncementCommand(
    val announcementRepository: AnnouncementRepository,
    val getInstructorById: GetInstructorById,
    val getCourseById: GetCourseById
) : Command<CreateAnnouncementDto, Int> {

    @PreAuthorize("hasRole('INSTRUCTOR')")
    override fun execute(params: CreateAnnouncementDto): Int {
        val instructorUser = SecurityContextHolder.getContext().authentication.principal as User

        val instructor = getInstructorById.execute(instructorUser.id)
        val course = getCourseById.execute(params.courseId)

        val announcement = announcementRepository.save(
            Announcement(
                title = params.title,
                content = params.content,
                author = instructor,
                course = course
            )
        )

        return announcement.id
    }
}