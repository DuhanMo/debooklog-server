package org.debooklog.debooklogserver.adapter.controller.book

import org.debooklog.debooklogserver.core.book.model.BookInformationData

data class BookInformationResponse(
    val title: String,
    val author: String,
    val isbn: List<String>,
    val thumbnail: String,
) {
    constructor(data: BookInformationData) : this(
        title = data.title,
        author = data.author,
        isbn = data.isbn,
        thumbnail = data.thumbnail,
    )
}
