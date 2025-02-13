package org.debooklog.adapter.controller.bookshelf

import org.debooklog.core.book.model.Book
import org.debooklog.core.book.model.BookLike
import org.debooklog.core.book.model.BookState
import org.debooklog.core.bookshelf.service.BookshelfWithBooks
import java.time.Instant

data class BookshelfDetailResponse(
    val id: Long,
    val memberId: Long,
    val name: String,
    val imageUrl: String?,
    val books: List<BookResponse>,
) {
    constructor(bookshelfWithBooks: BookshelfWithBooks) : this(
        id = bookshelfWithBooks.bookshelf.id,
        memberId = bookshelfWithBooks.member.id,
        name = bookshelfWithBooks.bookshelf.name,
        imageUrl = bookshelfWithBooks.bookshelf.imageUrl,
        books = bookshelfWithBooks.books.map { BookResponse.from(it, bookshelfWithBooks.bookLikes) },
    )

    data class BookResponse(
        val id: Long,
        val title: String,
        val author: String,
        val isbn: List<String>,
        val thumbnail: String,
        val likeCount: Int,
        val state: BookState,
        val bookLikes: List<BookLikeResponse>,
        val createdAt: Instant,
        val updatedAt: Instant,
    ) {
        companion object {
            fun from(
                book: Book,
                bookLikes: List<BookLike>,
            ): BookResponse {
                return BookResponse(
                    id = book.id,
                    title = book.title,
                    author = book.author,
                    isbn = book.isbn,
                    thumbnail = book.thumbnail,
                    likeCount = book.likeCount,
                    state = book.state,
                    bookLikes = BookLikeResponse.from(bookLikes.filter { it.bookId == book.id }),
                    createdAt = book.createdAt,
                    updatedAt = book.updatedAt,
                )
            }
        }
    }

    data class BookLikeResponse(
        val memberId: Long,
    ) {
        companion object {
            fun from(bookLikes: List<BookLike>): List<BookLikeResponse> {
                return bookLikes.map { BookLikeResponse(it.memberId) }
            }
        }
    }
}
