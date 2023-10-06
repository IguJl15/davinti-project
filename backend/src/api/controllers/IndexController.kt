package com.davintiproject.backend.api.controllers

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("")
class IndexController {

    @GetMapping()
    fun helloWorld(): String {
        return "Hello World"
    }
}