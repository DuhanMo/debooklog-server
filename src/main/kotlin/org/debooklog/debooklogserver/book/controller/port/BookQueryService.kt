package org.debooklog.debooklogserver.book.controller.port

import org.debooklog.debooklogserver.book.domain.BookInformationData
import org.debooklog.debooklogserver.book.domain.BookRank

interface BookQueryService {
    fun search(title: String): List<BookInformationData>

    fun findBookRanks(): List<BookRank>
}
