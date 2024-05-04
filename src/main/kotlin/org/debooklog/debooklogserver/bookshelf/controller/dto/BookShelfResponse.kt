package org.debooklog.debooklogserver.bookshelf.controller.dto

import org.debooklog.debooklogserver.bookshelf.domain.BookShelf

data class BookShelfResponse(
    val id: Long,
    val memberId: Long,
    val name: String,
) {
    companion object {
        fun from(bookShelf: BookShelf): BookShelfResponse {
            return BookShelfResponse(
                id = bookShelf.id!!,
                memberId = bookShelf.memberId,
                name = bookShelf.name,
            )
        }
    }
}
