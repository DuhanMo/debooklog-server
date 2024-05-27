package org.debooklog.debooklogserver.bookshelf.service.port

import org.debooklog.debooklogserver.bookshelf.domain.Bookshelf

interface BookshelfRepository {
    fun save(bookshelf: Bookshelf): Bookshelf

    fun getById(id: Long): Bookshelf

    fun findAll(): List<Bookshelf>

    fun findByMemberId(memberId: Long): Bookshelf?
}
