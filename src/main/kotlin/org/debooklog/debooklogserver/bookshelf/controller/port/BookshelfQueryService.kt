package org.debooklog.debooklogserver.bookshelf.controller.port

import org.debooklog.debooklogserver.bookshelf.domain.Bookshelf
import org.debooklog.debooklogserver.bookshelf.service.dto.BookshelfWithBooks

interface BookshelfQueryService {
    fun findAll(): List<Bookshelf>

    fun find(bookshelfId: Long): BookshelfWithBooks
}
