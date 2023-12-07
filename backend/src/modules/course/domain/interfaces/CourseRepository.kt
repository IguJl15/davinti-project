package com.davintiproject.backend.modules.course.domain.interfaces

import com.davintiproject.backend.modules.course.domain.entities.Course
import org.springframework.data.repository.ListCrudRepository
import org.springframework.stereotype.Repository

@Repository
interface CourseRepository : ListCrudRepository<Course, Int>

