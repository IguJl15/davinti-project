package com.davintiproject.backend.modules.lesson.domain.interfaces

import com.davintiproject.backend.modules.lesson.domain.entities.Content
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ContentRepository : JpaRepository<Content, Int> {
}