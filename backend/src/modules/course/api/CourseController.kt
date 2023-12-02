package com.davintiproject.backend.modules.course.api

import com.davintiproject.backend.modules.course.domain.commands.CreateCourseCommand
import com.davintiproject.backend.modules.course.domain.commands.CreateCourseDto
import com.davintiproject.backend.modules.course.domain.commands.DeleteCourseCommand
import com.davintiproject.backend.modules.course.domain.entities.Course
import com.davintiproject.backend.modules.course.domain.queries.GetAllAvailableCourses
import com.davintiproject.backend.modules.course.domain.queries.GetCourseById
import jakarta.persistence.EntityNotFoundException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.net.URI


@RequestMapping("/courses")
@RestController
class CourseController(
    val createCommand: CreateCourseCommand,
    val getAllQuery: GetAllAvailableCourses,
    val getByIdQuery: GetCourseById,
    val deleteCommand: DeleteCourseCommand
) {
    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    fun createCourse(@RequestBody dto: CreateCourseDto): ResponseEntity<String> {
        val id = createCommand.execute(dto)

        return ResponseEntity.created(URI("/courses/$id")).build()
    }

    @GetMapping("")
    fun getAll(): ResponseEntity<Collection<Course>> {
        val courses = getAllQuery.execute(Unit)

        return ResponseEntity.ok(courses)
    }

    @GetMapping("{id}")
    fun getById(@PathVariable id: Int): ResponseEntity<Course> {
        val course = getByIdQuery.execute(id)

        return ResponseEntity.ok(course)
    }

    @DeleteMapping("/{id}")
    fun deleteCourse(@PathVariable id: Int): ResponseEntity<Unit> {
        deleteCommand.execute(id)

        return ResponseEntity.noContent().build()
    }
}