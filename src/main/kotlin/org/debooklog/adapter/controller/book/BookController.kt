package org.debooklog.adapter.controller.book

import org.debooklog.adapter.controller.common.ApiResponse
import org.debooklog.adapter.security.LoginMember
import org.debooklog.core.book.service.BookLikeService
import org.debooklog.core.book.service.BookQueryService
import org.debooklog.core.book.service.BookService
import org.debooklog.core.member.model.Member
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/books")
class BookController(
    private val bookQueryService: BookQueryService,
    private val bookService: BookService,
    private val bookLikeService: BookLikeService,
) {
    @GetMapping("/search")
    fun search(title: String): ResponseEntity<ApiResponse<List<BookInformationResponse>>> {
        return ResponseEntity.ok(ApiResponse.of(bookQueryService.search(title).map(::BookInformationResponse)))
    }

    @PostMapping
    fun register(
        @RequestBody request: BookRegisterRequest,
        @LoginMember member: Member,
    ): ResponseEntity<ApiResponse<Unit>> {
        bookService.register(request.toCommand(member.id))
        return ResponseEntity.ok(ApiResponse.empty())
    }

    @DeleteMapping("/{bookId}")
    fun delete(
        @PathVariable bookId: Long,
        @LoginMember member: Member,
    ): ResponseEntity<ApiResponse<Unit>> {
        bookService.delete(bookId, member.id)
        return ResponseEntity.ok(ApiResponse.empty())
    }

    @GetMapping("/ranks")
    fun findBookRanks(): ResponseEntity<ApiResponse<List<BookRankResponse>>> {
        return ResponseEntity.ok(ApiResponse.of(bookQueryService.findBookRanks().map(::BookRankResponse)))
    }

    @PostMapping("/{bookId}/like")
    fun createLike(
        @PathVariable bookId: Long,
        @LoginMember member: Member,
    ): ResponseEntity<ApiResponse<Unit>> {
        bookLikeService.create(bookId, member.id)
        return ResponseEntity.ok(ApiResponse.empty())
    }

    @PostMapping("/{bookId}/cancel-like")
    fun cancelLike(
        @PathVariable bookId: Long,
        @LoginMember member: Member,
    ): ResponseEntity<ApiResponse<Unit>> {
        bookLikeService.remove(bookId, member.id)
        return ResponseEntity.ok(ApiResponse.empty())
    }

    @PostMapping("/{bookId}/reading")
    fun readNow(
        @PathVariable bookId: Long,
        @LoginMember member: Member,
    ): ResponseEntity<ApiResponse<Unit>> {
        bookService.reading(bookId, member.id)
        return ResponseEntity.ok(ApiResponse.empty())
    }

    @PostMapping("/{bookId}/done")
    fun done(
        @PathVariable bookId: Long,
        @LoginMember member: Member,
    ): ResponseEntity<ApiResponse<Unit>> {
        bookService.done(bookId, member.id)
        return ResponseEntity.ok(ApiResponse.empty())
    }
}
