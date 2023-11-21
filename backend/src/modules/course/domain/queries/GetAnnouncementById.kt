package com.davintiproject.backend.modules.course.domain.queries

import com.davintiproject.backend.common.domain.Query
import com.davintiproject.backend.modules.course.domain.entities.Announcement
import org.springframework.http.HttpStatus
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.stereotype.Component
import org.springframework.web.server.ResponseStatusException

data class GetAnnouncementByIdDto (
    val courseId: Int,
    val announcementId: Int
)

@Component
class GetAnnouncementById(
    val getCourse: GetCourseById
) : Query<GetAnnouncementByIdDto, Announcement> {
    @PreAuthorize("hasRole('INSTRUCTOR')")
    override fun execute(params: GetAnnouncementByIdDto): Announcement {
        val course = getCourse.execute(params.courseId)

        val announcement = course.announcements.singleOrNull { it.id == params.announcementId } ?: throw ResponseStatusException(
            HttpStatus.NOT_FOUND
        )

        return announcement
    }
}