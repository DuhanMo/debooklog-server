package org.debooklog.debooklogserver.book.service.dto

import org.debooklog.debooklogserver.book.domain.BookInformationData

data class BookInformationResponse(
    val title: String,
    val author: String,
    val isbn: String,
    val thumbnail: String,
) {
    constructor(data: BookInformationData) : this(
        data.title,
        data.author,
        data.isbn,
        data.thumbnail,
    )
}
