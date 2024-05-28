package org.debooklog.debooklogserver.book.service

import org.debooklog.debooklogserver.book.controller.port.BookQueryService
import org.debooklog.debooklogserver.book.domain.Book
import org.debooklog.debooklogserver.book.domain.BookInformationData
import org.debooklog.debooklogserver.book.domain.BookRank
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

    override fun findBookRanks(): List<BookRank> {
        val books = bookRepository.findAll()
        val isbnFrequency = books.groupingBy { it.isbn.first() }.eachCount()
        val isbnToBookTitle = books.associateBy { it.isbn.first() }.mapValues { it.value.title }
        val rankedIsbn = isbnFrequency.entries.sortedByDescending { it.value }

        return rankedIsbn.mapIndexed { index, (isbn, count) ->
            val bookTitle = isbnToBookTitle[isbn]
            BookRank(
                isbn = isbn,
                rank = index + 1,
                bookTitle = bookTitle ?: "",
                count = count,
            )
        }
    }
}
