package com.davintiproject.backend.modules.Student.domain.commands

import com.davintiproject.backend.common.domain.Command
import com.davintiproject.backend.modules.Student.domain.entities.Student
import com.davintiproject.backend.modules.Student.domain.interfaces.StudentRepository
import com.davintiproject.backend.modules.Student.domain.queries.GetStudentById
import com.davintiproject.backend.modules.course.domain.queries.GetCourseById
import org.springframework.http.HttpStatus
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.stereotype.Component
import org.springframework.web.server.ResponseStatusException

data class EnrollStudentInCourseDto(
    val studentId: Int,
    val courseId: Int
)

@Component
class EnrollStudentInCourseCommand(
    val studentRepository: StudentRepository,
    val getStudentById: GetStudentById,
    val getCourseById: GetCourseById
) : Command<EnrollStudentInCourseDto, Student> {

    @PreAuthorize("hasRole('STUDENT')")
    override fun execute(params: EnrollStudentInCourseDto): Student {

        val student = getStudentById.execute(params.studentId)
        val course = getCourseById.execute(params.courseId)

        if (student.enrolledCourses.any { it.id == course.id } ) {
            throw ResponseStatusException(HttpStatus.BAD_REQUEST, "Already enrolled")
        }

        student.enrolledCourses.add(course.course)

        studentRepository.save(student)

        return student
    }


}