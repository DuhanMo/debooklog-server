package org.debooklog.debooklogserver.member.controller

import org.debooklog.debooklogserver.common.controller.ApiResponse
import org.debooklog.debooklogserver.member.controller.dto.MemberResponse
import org.debooklog.debooklogserver.member.controller.port.MemberService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/members")
class MemberController(
    private val memberService: MemberService,
) {
    @GetMapping
    fun findAll(): ResponseEntity<List<MemberResponse>> {
        return ResponseEntity.ok(memberService.findAll().map(::MemberResponse))
    }

    @DeleteMapping("/{memberId}")
    fun withdrawal(
        @PathVariable("memberId") memberId: Long,
    ): ResponseEntity<ApiResponse<Unit>> {
        memberService.withdrawal(memberId)
        return ResponseEntity.ok(ApiResponse.empty())
    }
}
