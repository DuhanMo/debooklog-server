package org.debooklog.fixture

import org.debooklog.core.book.model.Book
import org.debooklog.core.book.model.BookState
import org.debooklog.core.book.model.BookState.DONE
import java.time.Instant
import java.time.Instant.now

fun createBookFixture(
    id: Long = 0,
    memberId: Long,
    bookshelfId: Long,
    title: String = "title",
    author: String = "author",
    isbn: List<String> = listOf("12345678"),
    thumbnail: String = "thumbnail",
    state: BookState = DONE,
    likeCount: Int = 0,
    deletedAt: Instant? = null,
    isDeleted: Boolean = false,
): Book {
    return Book(
        id = id,
        memberId = memberId,
        bookshelfId = bookshelfId,
        title = title,
        author = author,
        isbn = isbn,
        thumbnail = thumbnail,
        likeCount = likeCount,
        state = state,
        createdAt = now(),
        updatedAt = now(),
        deletedAt = deletedAt,
        isDeleted = isDeleted,
    )
}
