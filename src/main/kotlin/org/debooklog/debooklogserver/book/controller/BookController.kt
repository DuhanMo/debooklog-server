package org.debooklog.debooklogserver.book.controller

import org.debooklog.debooklogserver.book.service.BookQueryService
import org.debooklog.debooklogserver.book.service.dto.BookInformationResponse
import org.debooklog.debooklogserver.global.controller.ApiResponse
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/book")
class BookController(
    private val bookQueryService: BookQueryService,
) {
    @GetMapping("/search")
    fun search(title: String): ResponseEntity<ApiResponse<List<BookInformationResponse>>> {
        return ResponseEntity.ok(ApiResponse.of(bookQueryService.search(title)))
    }
}
