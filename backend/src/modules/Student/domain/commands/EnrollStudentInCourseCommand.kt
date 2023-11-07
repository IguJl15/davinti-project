package com.davintiproject.backend.modules.Student.domain.commands

import com.davintiproject.backend.common.domain.Command
import com.davintiproject.backend.modules.Student.domain.entities.Student
import com.davintiproject.backend.modules.Student.domain.interfaces.StudentRepository
import com.davintiproject.backend.modules.Student.domain.queries.GetStudentById
import com.davintiproject.backend.modules.course.domain.interfaces.CourseRepository
import com.davintiproject.backend.modules.course.domain.queries.GetCourseById
import org.springframework.http.HttpStatus
import org.springframework.security.access.prepost.PreAuthorize

import org.springframework.web.server.ResponseStatusException

data class EnrollStudentInCourseDto(
    val studentId: String,
    val courseId: String
)

class EnrollStudentInCourseCommand(
    val studentRepository: StudentRepository,
    val courseRepository: CourseRepository,
    val getStudentById: GetStudentById,
    val getCourseById: GetCourseById
) : Command<EnrollStudentInCourseDto, Student> {

    @PreAuthorize("hasRole('ADMIN') or authentication.principal.id == #params.studentId")
    override fun execute(params: EnrollStudentInCourseDto): Student {

        val student = getStudentById.execute(params.studentId)
        val course = getCourseById.execute(params.courseId)

        if (student.enrolledCourses.contains(course) && course.studentsEnrolled.contains(student)) {
            throw ResponseStatusException(HttpStatus.BAD_REQUEST)
        }

        val newStudents = (course.studentsEnrolled + student)
        courseRepository.save(course.copy(studentsEnrolled = newStudents))

        return getStudentById.execute(params.studentId)
    }


}