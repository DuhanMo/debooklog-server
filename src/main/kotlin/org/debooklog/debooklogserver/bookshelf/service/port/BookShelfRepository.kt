package org.debooklog.debooklogserver.bookshelf.service.port

import org.debooklog.debooklogserver.bookshelf.domain.BookShelf

interface BookShelfRepository {
    fun save(bookShelf: BookShelf): BookShelf

    fun getById(id: Long): BookShelf

    fun findAll(): List<BookShelf>
}
