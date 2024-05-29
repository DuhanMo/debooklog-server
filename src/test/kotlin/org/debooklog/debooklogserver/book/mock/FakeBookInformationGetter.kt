package org.debooklog.debooklogserver.book.mock

import org.debooklog.debooklogserver.book.domain.BookInformationData
import org.debooklog.debooklogserver.book.service.port.BookInformationGetter

class FakeBookInformationGetter(
    private val stub: List<BookInformationData> = emptyList(),
) : BookInformationGetter {
    override fun search(title: String): List<BookInformationData> {
        return stub.filter { title == it.title }
    }
}
