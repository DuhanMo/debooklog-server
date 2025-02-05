package org.debooklog.core.bookshelf.port

import org.debooklog.core.bookshelf.model.Bookshelf

interface BookshelfRepository {
    fun save(bookshelf: Bookshelf): Bookshelf

    fun getById(id: Long): Bookshelf

    fun findAll(): List<Bookshelf>

    fun findByMemberId(memberId: Long): Bookshelf?

    fun findBookshelvesSortedByLatestBook(): List<Bookshelf>
}
