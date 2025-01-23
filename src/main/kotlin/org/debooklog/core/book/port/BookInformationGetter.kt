package org.debooklog.core.book.port

import org.debooklog.core.book.model.BookInformationData

interface BookInformationGetter {
    fun search(title: String): List<BookInformationData>
}
