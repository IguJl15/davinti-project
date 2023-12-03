package com.davintiproject.backend.modules.instructor.api

import com.davintiproject.backend.modules.instructor.domain.commands.CreateInstructorCommand
import com.davintiproject.backend.modules.instructor.domain.commands.CreateInstructorDto
import com.davintiproject.backend.modules.instructor.domain.commands.DeleteInstructorCommand
import com.davintiproject.backend.modules.instructor.domain.entities.Instructor
import com.davintiproject.backend.modules.instructor.domain.queries.GetAllInstructors
import com.davintiproject.backend.modules.instructor.domain.queries.GetInstructorById
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.net.URI

@RequestMapping("/instructors")
@RestController
class InstructorController(
    val createCommand: CreateInstructorCommand,
    val getAllQuery: GetAllInstructors,
    val getByIdQuery: GetInstructorById,
    val deleteCommand: DeleteInstructorCommand
) {
    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    fun createInstructor(@RequestBody dto: CreateInstructorDto): ResponseEntity<String> {
        val id = createCommand.execute(dto)

        return ResponseEntity.created(URI("/instructors/$id")).build()
    }

    @GetMapping("")
    @ResponseStatus(HttpStatus.OK)
    fun getAll(): ResponseEntity<Collection<Instructor>> {
        val list = getAllQuery.execute(Unit)

        return ResponseEntity.ok(list)
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    fun getById(@PathVariable id: Int): ResponseEntity<Instructor> {
        val instructor = getByIdQuery.execute(id)

        return ResponseEntity.ok(instructor)
    }

    @DeleteMapping("/{id}")
    fun deleteInstructor(@PathVariable id: Int): ResponseEntity<Unit> {
        deleteCommand.execute(id)

        return ResponseEntity.noContent().build()
    }
}