package org.debooklog.debooklogserver.core.bookshelf.port

import org.debooklog.debooklogserver.core.bookshelf.model.Bookshelf

interface BookshelfRepository {
    fun save(bookshelf: Bookshelf): Bookshelf

    fun getById(id: Long): Bookshelf

    fun findAll(): List<Bookshelf>

    fun findByMemberId(memberId: Long): Bookshelf?
}
