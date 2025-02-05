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

        // ISBN별 빈도 계산
        val isbnFrequency = books.groupingBy { it.isbn.first() }.eachCount()

        // ISBN별 책 제목 매핑
        val bookTitleByIsbn = books.associateBy { it.isbn.first() }.mapValues { it.value.title }

        // ISBN별 최신 생성일 매핑
        val latestCreatedAtByIsbn =
            books
                .groupBy { it.isbn.first() }
                .mapValues { (_, bookList) -> bookList.maxOf { it.createdAt } }

        // 빈도 수가 높은 순 + 같은 빈도면 최신 생성일 기준 정렬
        val rankedIsbn =
            isbnFrequency.entries
                .sortedWith(
                    compareByDescending<Map.Entry<String, Int>> { it.value }
                        .thenByDescending { latestCreatedAtByIsbn[it.key] },
                )

        return rankedIsbn.take(BOOK_RANK_MAX_COUNT).mapIndexed { index, (isbn, count) ->
            val bookTitle = bookTitleByIsbn[isbn] ?: ""
            BookRank(
                isbn = isbn,
                rank = index + 1,
                bookTitle = bookTitle,
                count = count,
            )
        }
    }

    companion object {
        private const val BOOK_RANK_MAX_COUNT = 10
    }
}
