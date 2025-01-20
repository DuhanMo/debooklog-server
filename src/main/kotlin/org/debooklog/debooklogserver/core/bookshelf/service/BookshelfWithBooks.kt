package org.debooklog.debooklogserver.core.bookshelf.service

import org.debooklog.debooklogserver.core.book.model.Book
import org.debooklog.debooklogserver.core.bookshelf.model.Bookshelf

data class BookshelfWithBooks(
    val bookshelf: Bookshelf,
    val books: List<Book>,
)
