package org.debooklog.debooklogserver.bookshelf.controller.port

import org.debooklog.debooklogserver.bookshelf.domain.Bookshelf

interface BookshelfQueryService {
    fun findAll(): List<Bookshelf>
}
