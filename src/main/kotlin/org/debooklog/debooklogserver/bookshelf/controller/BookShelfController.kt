package org.debooklog.debooklogserver.bookshelf.controller

import org.debooklog.debooklogserver.bookshelf.controller.dto.BookShelfResponse
import org.debooklog.debooklogserver.bookshelf.controller.port.BookShelfQueryService
import org.debooklog.debooklogserver.common.controller.ApiResponse
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/bookshelves")
class BookShelfController(
    private val bookShelfQueryService: BookShelfQueryService,
) {
    @GetMapping
    fun findAll(): ResponseEntity<ApiResponse<List<BookShelfResponse>>> {
        return ResponseEntity.ok(ApiResponse.of(bookShelfQueryService.findAll().map(BookShelfResponse::from)))
    }
}
