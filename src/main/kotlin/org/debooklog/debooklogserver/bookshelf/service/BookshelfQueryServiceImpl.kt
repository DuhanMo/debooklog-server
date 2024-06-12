package org.debooklog.debooklogserver.bookshelf.service

import org.debooklog.debooklogserver.book.service.port.BookRepository
import org.debooklog.debooklogserver.bookshelf.controller.port.BookshelfQueryService
import org.debooklog.debooklogserver.bookshelf.domain.Bookshelf
import org.debooklog.debooklogserver.bookshelf.service.dto.BookshelfWithBooks
import org.debooklog.debooklogserver.bookshelf.service.port.BookshelfRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class BookshelfQueryServiceImpl(
    private val bookshelfRepository: BookshelfRepository,
    private val bookRepository: BookRepository,
) : BookshelfQueryService {
    override fun findAll(): List<Bookshelf> {
        return bookshelfRepository.findAll()
    }

    override fun find(bookshelfId: Long): BookshelfWithBooks {
        return BookshelfWithBooks(
            bookshelf = bookshelfRepository.getById(bookshelfId),
            books = bookRepository.findAllByBookshelfId(bookshelfId),
        )
    }
}
