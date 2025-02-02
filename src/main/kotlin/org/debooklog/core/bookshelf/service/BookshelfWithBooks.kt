package org.debooklog.core.bookshelf.service

import org.debooklog.core.book.model.Book
import org.debooklog.core.book.model.BookLike
import org.debooklog.core.bookshelf.model.Bookshelf
import org.debooklog.core.member.model.Member

data class BookshelfWithBooks(
    val bookshelf: Bookshelf,
    val member: Member,
    val books: List<Book>,
    val bookLikes: List<BookLike>,
)
