package org.debooklog.debooklogserver.book.controller

import org.debooklog.debooklogserver.book.controller.dto.BookInformationResponse
import org.debooklog.debooklogserver.book.controller.dto.BookListResponse
import org.debooklog.debooklogserver.book.controller.dto.BookRegisterRequest
import org.debooklog.debooklogserver.book.controller.port.BookQueryService
import org.debooklog.debooklogserver.book.controller.port.BookService
import org.debooklog.debooklogserver.common.controller.ApiResponse
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/books")
class BookController(
    private val bookQueryService: BookQueryService,
    private val bookService: BookService,
) {
    @GetMapping("/search")
    fun search(title: String): ResponseEntity<ApiResponse<List<BookInformationResponse>>> {
        return ResponseEntity.ok(ApiResponse.of(bookQueryService.search(title).map(BookInformationResponse::from)))
    }

    @PostMapping
    fun register(
        @RequestBody request: BookRegisterRequest,
    ): ResponseEntity<ApiResponse<Nothing>> {
        // TODO("멤버 아이디 인증객체에서 추출 후 전달")
        bookService.register(request.toCommand(1L))
        return ResponseEntity.ok(ApiResponse.empty())
    }

    @DeleteMapping("/{bookId}")
    fun delete(
        @PathVariable bookId: Long,
    ): ResponseEntity<ApiResponse<Nothing>> {
        bookService.delete(bookId)
        return ResponseEntity.ok(ApiResponse.empty())
    }

    @GetMapping
    fun findAllByBookshelfId(
        @RequestParam(name = "bookshelfId", required = true) bookshelfId: Long,
    ): ResponseEntity<ApiResponse<List<BookListResponse>>> {
        return ResponseEntity.ok(
            ApiResponse.of(bookQueryService.findAllByBookshelfId(bookshelfId).map(BookListResponse::from)),
        )
    }
}
