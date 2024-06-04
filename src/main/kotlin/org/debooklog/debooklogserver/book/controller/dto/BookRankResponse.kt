package org.debooklog.debooklogserver.book.controller.dto

import org.debooklog.debooklogserver.book.domain.BookRank

data class BookRankResponse(
    val rank: Int,
    val isbn: String,
    val bookTitle: String,
    val count: Int,
) {
    constructor(bookRank: BookRank) : this(
        rank = bookRank.rank,
        isbn = bookRank.isbn,
        bookTitle = bookRank.bookTitle,
        count = bookRank.count,
    )
}
