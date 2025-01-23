package org.debooklog.adapter.controller.bookshelf

import java.time.LocalDateTime

data class BookshelfDetailResponse(
    val id: Long,
    val name: String,
    val imageUrl: String?,
    val books: List<BookResponse>,
) {
    constructor(bookshelfWithBooks: org.debooklog.core.bookshelf.service.BookshelfWithBooks) : this(
        id = bookshelfWithBooks.bookshelf.id!!,
        name = bookshelfWithBooks.bookshelf.name,
        imageUrl = bookshelfWithBooks.bookshelf.imageUrl,
        books =
            bookshelfWithBooks.books.map {
                BookResponse(
                    id = it.id!!,
                    title = it.title,
                    author = it.author,
                    isbn = it.isbn,
                    thumbnail = it.thumbnail,
                    createdAt = it.createdAt,
                    updatedAt = it.updatedAt,
                )
            },
    )

    data class BookResponse(
        val id: Long,
        val title: String,
        val author: String,
        val isbn: List<String>,
        val thumbnail: String,
        val createdAt: LocalDateTime,
        val updatedAt: LocalDateTime,
    )
}
