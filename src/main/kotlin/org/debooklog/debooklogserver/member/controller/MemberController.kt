package org.debooklog.debooklogserver.member.controller

import org.debooklog.debooklogserver.member.controller.dto.MemberCreateRequest
import org.debooklog.debooklogserver.member.controller.port.MemberService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/members")
class MemberController(
    private val memberService: MemberService,
) {
    @PostMapping
    fun create(
        @RequestBody request: MemberCreateRequest,
    ): ResponseEntity<Unit> {
        memberService.create(request.toCommand())
        return ResponseEntity.ok().build()
    }
}
