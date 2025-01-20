package org.debooklog.debooklogserver.fixture

import org.debooklog.debooklogserver.core.book.model.Book
import org.debooklog.debooklogserver.core.book.model.BookState
import org.debooklog.debooklogserver.core.book.model.BookState.DONE
import java.time.LocalDateTime

fun createBookFixture(
    id: Long? = null,
    memberId: Long,
    bookshelfId: Long,
    title: String = "title",
    author: String = "author",
    isbn: List<String> = listOf("12345678"),
    thumbnail: String = "thumbnail",
    state: BookState = DONE,
    likeCount: Int = 0,
    deletedAt: LocalDateTime? = null,
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
        deletedAt = deletedAt,
        isDeleted = isDeleted,
    )
}
