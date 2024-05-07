package org.debooklog.debooklogserver.book.controller.dto

import org.debooklog.debooklogserver.book.domain.BookRegisterCommand

data class BookRegisterRequest(
    val bookshelfId: Long,
    val title: String,
    val author: String,
    val isbn: List<String>,
    val thumbnail: String,
) {
    fun toCommand(memberId: Long): BookRegisterCommand {
        return BookRegisterCommand(memberId, bookshelfId, title, author, isbn, thumbnail)
    }
}
