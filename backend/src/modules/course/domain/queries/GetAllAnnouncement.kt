package com.davintiproject.backend.modules.course.domain.queries

import com.davintiproject.backend.common.domain.Query
import com.davintiproject.backend.modules.course.domain.entities.Announcement
import com.davintiproject.backend.modules.course.domain.interfaces.AnnouncementRepository
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.stereotype.Component

@Component
class GetAllAnnouncement(
    val announcementRepository: AnnouncementRepository
) : Query<Int, Collection<Announcement>> {
    @PreAuthorize("hasRole('INSTRUCTOR')")
    override fun execute(params: Int): Collection<Announcement> {
        val announcements = announcementRepository.findByCourseId(params)

        return announcements
    }
}