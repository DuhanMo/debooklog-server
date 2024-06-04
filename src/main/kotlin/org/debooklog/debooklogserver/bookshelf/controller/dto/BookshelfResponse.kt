package org.debooklog.debooklogserver.bookshelf.controller.dto

import org.debooklog.debooklogserver.bookshelf.domain.Bookshelf

data class BookshelfResponse(
    val id: Long,
    val memberId: Long,
    val name: String,
) {
    constructor(bookshelf: Bookshelf) : this(
        id = bookshelf.id!!,
        memberId = bookshelf.memberId,
        name = bookshelf.name,
    )
}
