package com.davintiproject.backend.modules.Student.domain.queries

import com.davintiproject.backend.common.domain.Query
import com.davintiproject.backend.modules.Student.domain.entities.Student
import com.davintiproject.backend.modules.Student.domain.interfaces.StudentRepository
import com.davintiproject.backend.modules.course.domain.entities.Course
import com.davintiproject.backend.modules.course.domain.helpers.CourseAuthorizationService
import com.davintiproject.backend.modules.security.domain.entities.User
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component

@Component
class GetEnrolledCourses(
    private val courseAuthorizationService: CourseAuthorizationService,
    private val studentRepository: StudentRepository,
) : Query<Int, List<Course>> {
    override fun execute(params: Int): List<Course> {
        val user = SecurityContextHolder.getContext().authentication.principal as User

        if (user !is Student) {
            return emptyList()
        }

        val savedUser = studentRepository.findById(user.id).get()

        return savedUser.enrolledCourses.filter(courseAuthorizationService::userCanViewCourse)
    }
}