package org.debooklog.core.book.model

import org.debooklog.core.book.model.BookState.DONE
import org.debooklog.core.book.model.BookState.READING
import java.time.Instant
import java.time.Instant.now

data class Book(
    val id: Long,
    val memberId: Long,
    val bookshelfId: Long,
    val title: String,
    val author: String,
    val isbn: List<String>,
    val thumbnail: String,
    val likeCount: Int,
    val state: BookState,
    val createdAt: Instant,
    val updatedAt: Instant,
    val deletedAt: Instant?,
    val isDeleted: Boolean,
) {
    constructor(command: BookRegisterCommand, bookshelfId: Long, now: Instant) : this(
        id = 0,
        memberId = command.memberId,
        bookshelfId = bookshelfId,
        title = command.title,
        author = command.author,
        isbn = command.isbn,
        thumbnail = command.thumbnail,
        likeCount = 0,
        state = DONE,
        createdAt = now,
        updatedAt = now,
        deletedAt = null,
        isDeleted = false,
    )

    fun validateForDuplicate(books: List<Book>) {
        if (this.isbn.intersect(books.flatMap { it.isbn }.toSet()).isNotEmpty()) {
            throw DuplicateBookException()
        }
    }

    fun delete(memberId: Long): Book {
        if (this.memberId != memberId) {
            throw IllegalArgumentException("권한이 없습니다")
        }
        return this.copy(deletedAt = now(), isDeleted = true)
    }

    fun increaseLikeCount(): Book {
        return this.copy(likeCount = this.likeCount + 1, updatedAt = now())
    }

    fun decreaseLikeCount(): Book {
        return this.copy(likeCount = this.likeCount - 1, updatedAt = now())
    }

    fun reading(memberId: Long): Book {
        if (this.memberId != memberId) {
            throw IllegalArgumentException("권한이 없습니다")
        }
        return this.copy(state = READING, updatedAt = now())
    }

    fun done(memberId: Long): Book {
        if (this.memberId != memberId) {
            throw IllegalArgumentException("권한이 없습니다")
        }
        return this.copy(state = DONE, updatedAt = now())
    }
}
