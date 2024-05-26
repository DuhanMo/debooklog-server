package org.debooklog.debooklogserver.book.domain

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
    val createdAt: LocalDateTime = LocalDateTime.MAX,
    val updatedAt: LocalDateTime = LocalDateTime.MAX,
    val deletedAt: LocalDateTime?,
    val isDeleted: Boolean,
) {
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

    companion object {
        fun from(command: BookRegisterCommand): Book {
            return Book(
                id = null,
                memberId = command.memberId,
                bookshelfId = command.bookshelfId,
                title = command.title,
                author = command.author,
                isbn = command.isbn,
                thumbnail = command.thumbnail,
                deletedAt = null,
                isDeleted = false,
            )
        }
    }
}
