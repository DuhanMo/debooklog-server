package org.debooklog.debooklogserver.book.service.dto

data class BookRegisterCommand(
    val memberId: Long,
    val title: String,
    val author: String,
    val isbn: List<String>,
    val thumbnail: String,
)
