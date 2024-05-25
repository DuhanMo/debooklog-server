package org.debooklog.debooklogserver.book.controller.dto

import org.debooklog.debooklogserver.book.domain.Book
import java.time.LocalDateTime

data class BookListResponse(
    val id: Long,
    val bookshelfId: Long,
    val title: String,
    val author: String,
    val isbn: List<String>,
    val thumbnail: String,
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime,
) {
    companion object {
        fun from(book: Book): BookListResponse {
            return BookListResponse(
                id = book.id!!,
                bookshelfId = book.bookshelfId,
                title = book.title,
                author = book.author,
                isbn = book.isbn,
                thumbnail = book.thumbnail,
                createdAt = book.createdAt,
                updatedAt = book.updatedAt,
            )
        }
    }
}
