package org.debooklog.mock

import org.debooklog.core.book.model.BookInformationData
import org.debooklog.core.book.port.BookInformationGetter

class FakeBookInformationGetter(
    private val stub: List<BookInformationData> = emptyList(),
) : BookInformationGetter {
    override fun search(title: String): List<BookInformationData> {
        return stub.filter { title == it.title }
    }
}
