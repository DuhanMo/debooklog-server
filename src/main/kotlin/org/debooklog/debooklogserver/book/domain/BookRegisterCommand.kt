package org.debooklog.debooklogserver.book.domain

data class BookRegisterCommand(
    val memberId: Long,
    val title: String,
    val author: String,
    val isbn: List<String>,
    val thumbnail: String,
)
