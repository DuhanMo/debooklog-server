package org.debooklog.debooklogserver.bookshelf.controller.port

import org.debooklog.debooklogserver.bookshelf.domain.BookShelf

interface BookShelfQueryService {
    fun findAll(): List<BookShelf>
}
