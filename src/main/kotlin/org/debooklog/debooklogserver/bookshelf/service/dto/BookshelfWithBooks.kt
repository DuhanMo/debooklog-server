package org.debooklog.debooklogserver.bookshelf.service.dto

import org.debooklog.debooklogserver.book.domain.Book
import org.debooklog.debooklogserver.bookshelf.domain.Bookshelf

data class BookshelfWithBooks(
    val bookshelf: Bookshelf,
    val books: List<Book>,
)
