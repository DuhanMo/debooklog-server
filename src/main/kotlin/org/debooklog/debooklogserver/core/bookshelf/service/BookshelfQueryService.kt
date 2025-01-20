package org.debooklog.debooklogserver.bookshelf.service

import org.debooklog.debooklogserver.core.book.port.BookRepository
import org.debooklog.debooklogserver.core.bookshelf.model.Bookshelf
import org.debooklog.debooklogserver.core.bookshelf.port.BookshelfRepository
import org.debooklog.debooklogserver.core.bookshelf.service.BookshelfWithBooks
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class BookshelfQueryService(
    private val bookshelfRepository: BookshelfRepository,
    private val bookRepository: BookRepository,
) {
    fun findAll(): List<Bookshelf> {
        return bookshelfRepository.findAll()
    }

    fun find(bookshelfId: Long): BookshelfWithBooks {
        return BookshelfWithBooks(
            bookshelf = bookshelfRepository.getById(bookshelfId),
            books = bookRepository.findAllByBookshelfId(bookshelfId),
        )
    }
}
