package org.debooklog.debooklogserver.bookshelf.controller.dto

import org.debooklog.debooklogserver.bookshelf.domain.Bookshelf

data class BookshelfResponse(
    val id: Long,
    val memberId: Long,
    val name: String,
) {
    companion object {
        fun from(bookshelf: Bookshelf): BookshelfResponse {
            return BookshelfResponse(
                id = bookshelf.id!!,
                memberId = bookshelf.memberId,
                name = bookshelf.name,
            )
        }
    }
}
