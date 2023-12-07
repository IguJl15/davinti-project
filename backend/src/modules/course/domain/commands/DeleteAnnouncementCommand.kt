package com.davintiproject.backend.modules.course.domain.commands

import com.davintiproject.backend.common.domain.Command
import com.davintiproject.backend.modules.course.domain.interfaces.AnnouncementRepository
import com.davintiproject.backend.modules.course.domain.queries.GetAnnouncementById
import com.davintiproject.backend.modules.course.domain.queries.GetAnnouncementByIdDto
import com.davintiproject.backend.modules.instructor.domain.queries.GetInstructorById
import com.davintiproject.backend.modules.security.domain.entities.User
import org.springframework.http.HttpStatus
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component
import org.springframework.web.server.ResponseStatusException

@Component
class DeleteAnnouncementCommand(
    private val getAnnouncementById: GetAnnouncementById,
    private val getInstructor: GetInstructorById,
    private val announcementRepository: AnnouncementRepository
) : Command<GetAnnouncementByIdDto, Unit>{
    @PreAuthorize("hasRole('INSTRUCTOR') or hasRole('ADMIN')")
    override fun execute(params: GetAnnouncementByIdDto) {
        val isAdmin = SecurityContextHolder.getContext().authentication.authorities
            .any { it.authority == "ROLE_ADMIN" }

        if (!isAdmin) {
            // Se não for um administrador, verificar se é o instrutor correto
            val user = SecurityContextHolder.getContext().authentication.principal as User
            val instructor = getInstructor.execute(user.id)

            if (instructor.courses.none { it.id == params.courseId }) {
                throw ResponseStatusException(HttpStatus.FORBIDDEN, "You don't have access to this resource")
            }
        }

        val announcement = getAnnouncementById.execute(params)
        announcementRepository.deleteById(announcement.id)
    }
}