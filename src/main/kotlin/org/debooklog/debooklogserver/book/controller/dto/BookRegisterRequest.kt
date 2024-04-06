package org.debooklog.debooklogserver.book.controller.dto

import org.debooklog.debooklogserver.book.service.dto.BookRegisterCommand

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
