package org.debooklog.debooklogserver.book.domain

import java.time.LocalDateTime

class Book(
    val id: Long?,
    val memberId: Long,
    val title: String,
    val author: String,
    val isbn: List<String>,
    val thumbnail: String,
    var createdAt: LocalDateTime = LocalDateTime.MAX,
    var updatedAt: LocalDateTime = LocalDateTime.MAX,
) {
    companion object {
        fun from(command: BookRegisterCommand): Book {
            return Book(
                id = null,
                memberId = command.memberId,
                title = command.title,
                author = command.author,
                isbn = command.isbn,
                thumbnail = command.thumbnail,
            )
        }
    }

    fun validateForDuplicate(books: List<Book>) {
        if (this.isbn.intersect(books.flatMap { it.isbn }.toSet()).isNotEmpty()) {
            throw DuplicateBookException()
        }
    }
}
