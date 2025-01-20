package org.debooklog.debooklogserver.core.book.model

data class BookRegisterCommand(
    val memberId: Long,
    val title: String,
    val author: String,
    val isbn: List<String>,
    val thumbnail: String,
)
