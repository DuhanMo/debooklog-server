package org.debooklog.debooklogserver.book.service.port

import org.debooklog.debooklogserver.book.domain.Book

interface BookRepository {
    fun findAllByMemberId(memberId: Long): List<Book>

    fun save(book: Book): Book

    fun getById(bookId: Long): Book

    fun findAllByBookshelfId(bookshelfId: Long): List<Book>

    fun findAll(): List<Book>
}
