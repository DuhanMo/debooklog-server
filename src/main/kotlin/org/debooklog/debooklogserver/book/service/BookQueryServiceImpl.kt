package org.debooklog.debooklogserver.book.service

import org.debooklog.debooklogserver.book.controller.port.BookQueryService
import org.debooklog.debooklogserver.book.domain.Book
import org.debooklog.debooklogserver.book.domain.BookInformationData
import org.debooklog.debooklogserver.book.service.port.BookInformationGetter
import org.debooklog.debooklogserver.book.service.port.BookRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class BookQueryServiceImpl(
    private val bookInformationGetter: BookInformationGetter,
    private val bookRepository: BookRepository,
) : BookQueryService {
    override fun search(title: String): List<BookInformationData> {
        return bookInformationGetter.search(title)
    }

    override fun findAllByBookshelfId(bookshelfId: Long): List<Book> {
        return bookRepository.findAllByBookshelfId(bookshelfId)
    }
}
