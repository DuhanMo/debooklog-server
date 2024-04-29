package org.debooklog.debooklogserver.book.domain

class Book(
    val memberId: Long,
    val title: String,
    val author: String,
    val isbn: List<String>,
    val thumbnail: String,
) {
    companion object {
        fun from(command: BookRegisterCommand): Book {
            return Book(
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
