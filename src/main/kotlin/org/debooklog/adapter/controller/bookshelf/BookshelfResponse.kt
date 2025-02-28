package org.debooklog.adapter.controller.bookshelf

import org.debooklog.core.bookshelf.model.Bookshelf

data class BookshelfResponse(
    val id: Long,
    val memberId: Long,
    val name: String,
    val imageUrl: String?,
) {
    constructor(bookshelf: Bookshelf) : this(
        id = bookshelf.id,
        memberId = bookshelf.memberId,
        name = bookshelf.name,
        imageUrl = bookshelf.imageUrl,
    )
}
