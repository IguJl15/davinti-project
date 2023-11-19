package com.davintiproject.backend.modules.lesson.api

import com.davintiproject.backend.modules.lesson.domain.commands.CreateLessonCommand
import com.davintiproject.backend.modules.lesson.domain.commands.CreateLessonDto
import com.davintiproject.backend.modules.lesson.domain.commands.DeleteLessonCommand
import com.davintiproject.backend.modules.lesson.domain.queries.AllCourseLessonsView
import com.davintiproject.backend.modules.lesson.domain.queries.GetAllLessons
import com.davintiproject.backend.modules.lesson.domain.queries.GetLessonById
import com.davintiproject.backend.modules.lesson.domain.queries.GetLessonByIdDto
import com.davintiproject.backend.modules.lesson.domain.views.LessonView
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.net.URI

@RequestMapping("/courses/{courseId}/lessons")
@RestController
class LessonController(
    val createCommand: CreateLessonCommand,
    val getAllQuery: GetAllLessons,
    val getByIdQuery: GetLessonById,
    val deleteLesson: DeleteLessonCommand
) {
    @PostMapping("")
    fun createLesson(@RequestBody params: CreateLessonDto, @PathVariable courseId: Int): ResponseEntity<String> {
        val lessonId = createCommand.execute(params.copy(courseId = courseId))

        return ResponseEntity.created(URI("/courses/$courseId/lessons/$lessonId")).build()
    }

    @GetMapping("")
    fun getAll(@PathVariable courseId: Int): ResponseEntity<AllCourseLessonsView> {
        val lessons = getAllQuery.execute(courseId)

        return ResponseEntity.ok(lessons)
    }

    @GetMapping("/{lessonId}")
    fun getById(@PathVariable courseId: Int, @PathVariable lessonId: Int): ResponseEntity<LessonView> {
        val lessonDto = GetLessonByIdDto(courseId, lessonId)
        val lesson = getByIdQuery.execute(lessonDto)

        return ResponseEntity.ok(lesson)
    }

    @DeleteMapping("/{lessonId}")
    fun deleteLesson(@PathVariable lessonId: Int, @PathVariable courseId: Int): ResponseEntity<Unit> {
        deleteLesson.execute(GetLessonByIdDto(courseId, lessonId))

        return ResponseEntity.noContent().build()
    }
}