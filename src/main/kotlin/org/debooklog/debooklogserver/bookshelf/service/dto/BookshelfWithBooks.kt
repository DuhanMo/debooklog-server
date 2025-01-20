package org.debooklog.debooklogserver.bookshelf.service.dto

import org.debooklog.debooklogserver.bookshelf.domain.Bookshelf
import org.debooklog.debooklogserver.core.book.model.Book

data class BookshelfWithBooks(
    val bookshelf: Bookshelf,
    val books: List<Book>,
)
