package com.davintiproject.backend.modules.course.domain.queries

import com.davintiproject.backend.common.domain.Query
import com.davintiproject.backend.modules.course.domain.entities.Course
import com.davintiproject.backend.modules.course.domain.helpers.CourseAuthorizationService
import com.davintiproject.backend.modules.course.domain.interfaces.CourseRepository
import org.springframework.stereotype.Component

// class AllCoursesAvailable(
//     val courses: List<Course>
// ) : View

@Component
class GetAllAvailableCourses(
    val courseRepository: CourseRepository,
    val courseAuthorization: CourseAuthorizationService
) : Query<Unit, Collection<Course>> {
    override fun execute(params: Unit): List<Course> {
        val list = courseRepository.findAll()

        return list.filter(courseAuthorization::userCanViewCourse)
    }
}