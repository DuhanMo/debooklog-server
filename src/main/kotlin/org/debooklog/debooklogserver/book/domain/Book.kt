package org.debooklog.debooklogserver.book.domain

import org.debooklog.debooklogserver.book.domain.BookState.DONE
import org.debooklog.debooklogserver.book.domain.BookState.READING
import java.time.LocalDateTime
import java.time.LocalDateTime.now

data class Book(
    val id: Long?,
    val memberId: Long,
    val bookshelfId: Long,
    val title: String,
    val author: String,
    val isbn: List<String>,
    val thumbnail: String,
    val likeCount: Int,
    val state: BookState,
    val createdAt: LocalDateTime = LocalDateTime.MAX,
    val updatedAt: LocalDateTime = LocalDateTime.MAX,
    val deletedAt: LocalDateTime?,
    val isDeleted: Boolean,
) {
    constructor(command: BookRegisterCommand, bookshelfId: Long) : this(
        id = null,
        memberId = command.memberId,
        bookshelfId = bookshelfId,
        title = command.title,
        author = command.author,
        isbn = command.isbn,
        thumbnail = command.thumbnail,
        likeCount = 0,
        state = DONE,
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
