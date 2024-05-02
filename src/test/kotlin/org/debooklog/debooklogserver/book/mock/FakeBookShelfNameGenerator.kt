package org.debooklog.debooklogserver.book.mock

import org.debooklog.debooklogserver.bookshelf.service.port.BookShelfNameGenerator

class FakeBookShelfNameGenerator(
    private val name: String,
) : BookShelfNameGenerator {
    override fun generate(): String {
        return name
    }
}
