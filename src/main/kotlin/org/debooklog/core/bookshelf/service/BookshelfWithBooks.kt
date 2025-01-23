package org.debooklog.core.bookshelf.service

import org.debooklog.core.book.model.Book
import org.debooklog.core.bookshelf.model.Bookshelf

data class BookshelfWithBooks(
    val bookshelf: Bookshelf,
    val books: List<Book>,
)
