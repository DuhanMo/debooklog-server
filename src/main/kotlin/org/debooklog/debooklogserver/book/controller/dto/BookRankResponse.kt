package org.debooklog.debooklogserver.book.controller.dto

import org.debooklog.debooklogserver.book.domain.BookRank

data class BookRankResponse(
    val rank: Int,
    val isbn: String,
    val bookTitle: String,
    val count: Int,
) {
    companion object {
        fun from(bookRank: BookRank): BookRankResponse {
            return BookRankResponse(bookRank.rank, bookRank.isbn, bookRank.bookTitle, bookRank.count)
        }
    }
}
