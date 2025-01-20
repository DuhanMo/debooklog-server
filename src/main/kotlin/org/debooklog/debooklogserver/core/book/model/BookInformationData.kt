package org.debooklog.debooklogserver.core.book.model

data class BookInformationData(
    val title: String,
    val author: String,
    val isbn: List<String>,
    val thumbnail: String,
)
