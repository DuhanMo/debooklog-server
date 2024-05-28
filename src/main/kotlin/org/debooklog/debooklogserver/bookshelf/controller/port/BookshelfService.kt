package org.debooklog.debooklogserver.bookshelf.controller.port

interface BookshelfService {
    fun update(
        bookshelfId: Long,
        name: String,
        memberId: Long,
    )
}
