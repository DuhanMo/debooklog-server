package org.debooklog.debooklogserver.book.controller.port

import org.debooklog.debooklogserver.book.domain.Book
import org.debooklog.debooklogserver.book.domain.BookInformationData
import org.debooklog.debooklogserver.book.domain.BookRank

interface BookQueryService {
    fun search(title: String): List<BookInformationData>

    fun findAllByBookshelfId(bookshelfId: Long): List<Book>

    fun findBookRanks(): List<BookRank>
}
