package org.debooklog.debooklogserver.book.controller.dto

import org.debooklog.debooklogserver.book.domain.BookInformationData

data class BookInformationResponse(
    val title: String,
    val author: String,
    val isbn: List<String>,
    val thumbnail: String,
) {
    companion object {
        fun from(data: BookInformationData): BookInformationResponse {
            return BookInformationResponse(
                data.title,
                data.author,
                data.isbn,
                data.thumbnail,
            )
        }
    }
}
