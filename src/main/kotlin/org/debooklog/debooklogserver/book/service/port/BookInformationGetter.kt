package org.debooklog.debooklogserver.book.service.port

import org.debooklog.debooklogserver.book.domain.BookInformationData

interface BookInformationGetter {
    fun search(title: String): List<BookInformationData>
}
