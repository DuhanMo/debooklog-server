package org.debooklog.debooklogserver.bookshelf.controller

import org.debooklog.debooklogserver.bookshelf.controller.dto.BookshelfDetailResponse
import org.debooklog.debooklogserver.bookshelf.controller.dto.BookshelfResponse
import org.debooklog.debooklogserver.bookshelf.controller.dto.BookshelfUpdateRequest
import org.debooklog.debooklogserver.bookshelf.controller.port.BookshelfQueryService
import org.debooklog.debooklogserver.bookshelf.controller.port.BookshelfService
import org.debooklog.debooklogserver.common.controller.ApiResponse
import org.debooklog.debooklogserver.common.security.LoginMember
import org.debooklog.debooklogserver.member.domain.Member
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/bookshelves")
class BookshelfController(
    private val bookshelfQueryService: BookshelfQueryService,
    private val bookshelfService: BookshelfService,
) {
    @GetMapping
    fun findAll(): ResponseEntity<ApiResponse<List<BookshelfResponse>>> {
        return ResponseEntity.ok(ApiResponse.of(bookshelfQueryService.findAll().map(::BookshelfResponse)))
    }

    @GetMapping("/{bookshelfId}")
    fun find(
        @PathVariable bookshelfId: Long,
    ): ResponseEntity<ApiResponse<BookshelfDetailResponse>> {
        return ResponseEntity.ok(ApiResponse.of(BookshelfDetailResponse(bookshelfQueryService.find(bookshelfId))))
    }

    @PostMapping("/{bookshelfId}")
    fun update(
        @PathVariable bookshelfId: Long,
        @RequestBody request: BookshelfUpdateRequest,
        @LoginMember member: Member,
    ): ResponseEntity<ApiResponse<Unit>> {
        bookshelfService.update(bookshelfId, request.name, member.id!!)
        return ResponseEntity.ok(ApiResponse.empty())
    }
}
