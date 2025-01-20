package org.debooklog.debooklogserver.adapter.controller.book

import org.debooklog.debooklogserver.core.book.model.BookRegisterCommand

data class BookRegisterRequest(
    val title: String,
    val author: String,
    val isbn: List<String>,
    val thumbnail: String,
) {
    fun toCommand(memberId: Long): BookRegisterCommand {
        return BookRegisterCommand(memberId, title, author, isbn, thumbnail)
    }
}
