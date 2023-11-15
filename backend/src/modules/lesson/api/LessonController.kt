package com.davintiproject.backend.modules.lesson.api

import com.davintiproject.backend.modules.lesson.domain.commands.CreateLessonCommand
import com.davintiproject.backend.modules.lesson.domain.commands.CreateLessonDto
import com.davintiproject.backend.modules.lesson.domain.entities.Lesson
import com.davintiproject.backend.modules.lesson.domain.queries.GetAllLessons
import com.davintiproject.backend.modules.lesson.domain.queries.GetLessonById
import com.davintiproject.backend.modules.lesson.domain.queries.GetLessonByIdDto
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.net.URI

@RequestMapping("/courses/{id}/lessons")
@RestController
class LessonController(
    val createCommand: CreateLessonCommand,
    val getAllQuery: GetAllLessons,
    val getByIdQuery: GetLessonById
) {
    @RequestMapping("")
    @PostMapping
    fun createLesson(@RequestBody params: CreateLessonDto): ResponseEntity<String> {
        val id = createCommand.execute(params)

        return ResponseEntity.created(URI("/courses/{id}/lessons/$id")).build()
    }

    @RequestMapping("")
    @GetMapping
    fun getAll(@PathVariable courseId: Int): ResponseEntity<Collection<Lesson>> {
        val lessons = getAllQuery.execute(courseId)

        return ResponseEntity.ok(lessons)
    }

    @RequestMapping("/{id}")
    @GetMapping
    fun getById(@PathVariable courseId: Int, @PathVariable lessonId: Int): ResponseEntity<Lesson>{
        val lessonDto = GetLessonByIdDto(courseId, lessonId)
        val lesson = getByIdQuery.execute(lessonDto)

        return ResponseEntity.ok(lesson)
    }
}