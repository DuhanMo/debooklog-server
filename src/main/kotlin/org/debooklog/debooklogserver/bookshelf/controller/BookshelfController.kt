package org.debooklog.debooklogserver.bookshelf.controller

import org.debooklog.debooklogserver.bookshelf.controller.dto.BookshelfResponse
import org.debooklog.debooklogserver.bookshelf.controller.port.BookshelfQueryService
import org.debooklog.debooklogserver.common.controller.ApiResponse
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/bookshelves")
class BookshelfController(
    private val bookshelfQueryService: BookshelfQueryService,
) {
    @GetMapping
    fun findAll(): ResponseEntity<ApiResponse<List<BookshelfResponse>>> {
        return ResponseEntity.ok(ApiResponse.of(bookshelfQueryService.findAll().map(BookshelfResponse::from)))
    }
}
