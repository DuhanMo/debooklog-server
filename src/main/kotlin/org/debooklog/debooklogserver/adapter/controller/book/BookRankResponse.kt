package org.debooklog.debooklogserver.adapter.controller.book

import org.debooklog.debooklogserver.core.book.model.BookRank

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
