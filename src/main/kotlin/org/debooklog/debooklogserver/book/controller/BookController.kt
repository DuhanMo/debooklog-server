package org.debooklog.debooklogserver.book.controller

import org.debooklog.debooklogserver.book.controller.dto.BookRegisterRequest
import org.debooklog.debooklogserver.book.service.BookQueryService
import org.debooklog.debooklogserver.book.service.BookService
import org.debooklog.debooklogserver.book.service.dto.BookInformationResponse
import org.debooklog.debooklogserver.common.controller.ApiResponse
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/books")
class BookController(
    private val bookQueryService: BookQueryService,
    private val bookService: BookService,
) {
    @GetMapping("/search")
    fun search(title: String): ResponseEntity<ApiResponse<List<BookInformationResponse>>> {
        return ResponseEntity.ok(ApiResponse.of(bookQueryService.search(title)))
    }

    @PostMapping
    fun register(
        @RequestBody request: BookRegisterRequest,
    ): ResponseEntity<Unit> {
        bookService.register(request.toCommand(1L))
        return ResponseEntity.noContent().build()
    }
}
