package org.debooklog.core.bookshelf.service

import org.debooklog.core.book.port.BookRepository
import org.debooklog.core.bookshelf.model.Bookshelf
import org.debooklog.core.bookshelf.port.BookshelfRepository
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
