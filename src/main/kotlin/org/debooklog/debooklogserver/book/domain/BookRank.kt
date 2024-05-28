package org.debooklog.debooklogserver.book.domain

data class BookRank(
    val rank: Int,
    val isbn: String,
    val bookTitle: String,
    val count: Int,
)
