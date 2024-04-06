package org.debooklog.debooklogserver.book.domain

data class BookInformationData(
    val title: String,
    val author: String,
    val isbn: List<String>,
    val thumbnail: String,
)
