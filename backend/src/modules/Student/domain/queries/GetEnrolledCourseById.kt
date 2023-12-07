package com.davintiproject.backend.modules.Student.domain.queries

import com.davintiproject.backend.common.domain.Query
import com.davintiproject.backend.modules.course.domain.queries.CourseView
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Component
import org.springframework.web.server.ResponseStatusException

data class GetEnrolledCourseByIdDto(
    val studentId: Int,
    val courseId: Int
)

@Component
class GetEnrolledCourseById(
    val getEnrolledCourses: GetEnrolledCourses
) : Query<GetEnrolledCourseByIdDto, CourseView> {
    override fun execute(params: GetEnrolledCourseByIdDto): CourseView {
        val enrolledCourses = getEnrolledCourses.execute(params.studentId)

        val course = enrolledCourses.find { it.id == params.courseId }

        if(course != null) {
            return course
        } else {
            throw ResponseStatusException(HttpStatus.NOT_FOUND)
        }
    }
}