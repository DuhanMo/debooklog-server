package org.debooklog.debooklogserver.book.controller.port

import org.debooklog.debooklogserver.book.domain.Book
import org.debooklog.debooklogserver.book.domain.BookInformationData

interface BookQueryService {
    fun search(title: String): List<BookInformationData>

    fun findAllByBookshelfId(bookshelfId: Long): List<Book>
}
