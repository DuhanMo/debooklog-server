package org.debooklog.debooklogserver.auth.controller

import org.debooklog.debooklogserver.global.security.LoginMember
import org.debooklog.debooklogserver.member.domain.Member
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api")
class AuthController {
    @GetMapping("/all")
    fun member(): String {
        return "permitAll 😃"
    }

    @GetMapping("/secure")
    fun auth(
        @LoginMember member: Member,
    ): String {
        return "secure 🔐"
    }
}
