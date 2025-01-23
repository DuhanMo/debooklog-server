package org.debooklog.core.book.port

import org.debooklog.core.book.model.Book

interface BookRepository {
    fun findAllByMemberId(memberId: Long): List<Book>

    fun save(book: Book): Book

    fun getById(bookId: Long): Book

    fun findAllByBookshelfId(bookshelfId: Long): List<Book>

    fun findAll(): List<Book>
}
