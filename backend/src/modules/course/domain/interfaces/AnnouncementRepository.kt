package com.davintiproject.backend.modules.course.domain.interfaces

import com.davintiproject.backend.modules.course.domain.entities.Announcement
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface AnnouncementRepository : JpaRepository<Announcement, Int> {
    fun findByCourseId(courseId: Int): Collection<Announcement>
}