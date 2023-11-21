package com.davintiproject.backend.modules.course.api

import com.davintiproject.backend.modules.course.domain.commands.CreateAnnouncementCommand
import com.davintiproject.backend.modules.course.domain.commands.CreateAnnouncementDto
//import com.davintiproject.backend.modules.course.domain.commands.DeleteAnnouncementCommand
import com.davintiproject.backend.modules.course.domain.entities.Announcement
import com.davintiproject.backend.modules.course.domain.queries.GetAllAnnouncement
import com.davintiproject.backend.modules.course.domain.queries.GetAnnouncementById
import com.davintiproject.backend.modules.course.domain.queries.GetAnnouncementByIdDto
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import java.net.URI

@RequestMapping("/courses/{courseId}/announcements")
@RestController
class AnnouncementController (
    val createCommand: CreateAnnouncementCommand,
    val getAllQuery: GetAllAnnouncement,
    val getByIdQuery: GetAnnouncementById
//    val deleteCommand: DeleteAnnouncementCommand
) {
    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    fun createAnnouncement(@RequestBody dto: CreateAnnouncementDto, @PathVariable courseId: Int): ResponseEntity<String> {
        val id = createCommand.execute(dto.copy(courseId = courseId))

        return ResponseEntity.created(URI("/announcements/$id")).build()
    }

    @GetMapping("")
    @ResponseStatus(HttpStatus.OK)
    fun getAll(@PathVariable courseId: Int): ResponseEntity<Collection<Announcement>> {
        val announcements = getAllQuery.execute(courseId)

        return ResponseEntity.ok(announcements)
    }

    @GetMapping("/{announcementId}")
    @ResponseStatus(HttpStatus.OK)
    fun getById(@PathVariable courseId: Int, @PathVariable announcementId: Int): ResponseEntity<Announcement> {
        val announcement = getByIdQuery.execute(GetAnnouncementByIdDto(courseId, announcementId))

        return ResponseEntity.ok(announcement)
    }

//    @DeleteMapping("/{announcementId}")
//    @ResponseStatus(HttpStatus.NO_CONTENT)
//    fun deleteAnnouncement(@PathVariable courseId: Int, @PathVariable announcementId: Int): ResponseEntity<Unit> {
//        deleteCommand.execute(GetAnnouncementByIdDto(courseId,announcementId))
//
//        return ResponseEntity.noContent().build()
//    }
}