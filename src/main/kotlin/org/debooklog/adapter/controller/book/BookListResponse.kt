package org.debooklog.adapter.controller.book

import org.debooklog.core.book.model.Book
import java.time.Instant

data class BookListResponse(
    val id: Long,
    val bookshelfId: Long,
    val title: String,
    val author: String,
    val isbn: List<String>,
    val thumbnail: String,
    val createdAt: Instant,
    val updatedAt: Instant,
) {
    constructor(book: Book) : this(
        id = book.id,
        bookshelfId = book.bookshelfId,
        title = book.title,
        author = book.author,
        isbn = book.isbn,
        thumbnail = book.thumbnail,
        createdAt = book.createdAt,
        updatedAt = book.updatedAt,
    )
}
