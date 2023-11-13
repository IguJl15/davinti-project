package com.davintiproject.backend.modules.Student.api

import com.davintiproject.backend.modules.Student.domain.commands.CreateStudentCommand
import com.davintiproject.backend.modules.Student.domain.commands.CreateStudentDto
import com.davintiproject.backend.modules.Student.domain.commands.EnrollStudentInCourseCommand
import com.davintiproject.backend.modules.Student.domain.commands.EnrollStudentInCourseDto
import com.davintiproject.backend.modules.Student.domain.entities.Student
import com.davintiproject.backend.modules.Student.domain.queries.GetAllStudents
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.net.URI

@RequestMapping("/students")
@RestController
class StudentController(
    val createCommand: CreateStudentCommand,
    val getAllQuery: GetAllStudents,
    val enrollStudentInCourse: EnrollStudentInCourseCommand
) {
    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    fun createStudent(@RequestBody dto: CreateStudentDto): ResponseEntity<String> {
        val id = createCommand.execute(dto)

        return ResponseEntity.created(URI("/students/$id")).build()
    }

    @GetMapping("")
    fun getAll(): ResponseEntity<Collection<Student>> {
        val students = getAllQuery.execute(Unit)

        return ResponseEntity.ok(students)
    }

    @PostMapping("/{studentId}/courses/{courseId}")
    fun enrolls(@PathVariable studentId: Int, @PathVariable courseId: Int): ResponseEntity<Student> {
        val enrollStudentInCourseDto = EnrollStudentInCourseDto(studentId, courseId)
        val result = enrollStudentInCourse.execute(enrollStudentInCourseDto)

        return ResponseEntity.created(URI("/students/$studentId")).body(result)
    }
}