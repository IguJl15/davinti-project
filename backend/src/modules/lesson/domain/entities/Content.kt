package com.davintiproject.backend.modules.lesson.domain.entities

import jakarta.persistence.*

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "content_type", discriminatorType = DiscriminatorType.STRING)
sealed class Content(
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    val id: Int = 0,
    val title: String
)

@Entity
@DiscriminatorValue("document")
class DocumentContent(
    title: String,
    val filePath: String
) : Content(title = title)

@Entity
@DiscriminatorValue("url")
class UrlContent(
    title: String,
    val url: String,
) : Content(title = title)

@Entity
@DiscriminatorValue("video")
class VideoContent(
    title: String,
    val videoUrl: String
) : Content(title = title)