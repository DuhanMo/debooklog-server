package org.debooklog.debooklogserver.core.book.port

import org.debooklog.debooklogserver.core.book.model.BookInformationData

interface BookInformationGetter {
    fun search(title: String): List<BookInformationData>
}
