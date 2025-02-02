package org.debooklog.core.book.service

import org.debooklog.core.book.model.BookInformationData
import org.debooklog.core.book.model.BookRank
import org.debooklog.core.book.port.BookInformationGetter
import org.debooklog.core.book.port.BookRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class BookQueryService(
    private val bookInformationGetter: BookInformationGetter,
    private val bookRepository: BookRepository,
) {
    fun search(title: String): List<BookInformationData> {
        return bookInformationGetter.search(title)
    }

    fun findBookRanks(): List<BookRank> {
        val books = bookRepository.findAll()
        val isbnFrequency = books.groupingBy { it.isbn.first() }.eachCount()
        val isbnToBookTitle = books.associateBy { it.isbn.first() }.mapValues { it.value.title }
        val rankedIsbn = isbnFrequency.entries.sortedByDescending { it.value }

        return rankedIsbn.take(BOOK_RANK_MAX_COUNT).mapIndexed { index, (isbn, count) ->
            val bookTitle = isbnToBookTitle[isbn]
            BookRank(
                isbn = isbn,
                rank = index + 1,
                bookTitle = bookTitle ?: "",
                count = count,
            )
        }
    }

    companion object {
        private const val BOOK_RANK_MAX_COUNT = 5
    }
}
