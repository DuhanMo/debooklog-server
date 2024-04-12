package org.debooklog.debooklogserver.auth.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api")
class AuthController {
    @GetMapping("/auth")
    fun member(): String {
        return "permitAll 😃"
    }

    @GetMapping("/secure")
    fun auth(): String {
        return "secure 🔐"
    }
}
