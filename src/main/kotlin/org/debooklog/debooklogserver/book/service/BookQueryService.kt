package org.debooklog.debooklogserver.book.service

import org.debooklog.debooklogserver.book.domain.BookInformationGetter
import org.debooklog.debooklogserver.book.service.dto.BookInformationResponse
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class BookQueryService(
    private val bookInformationGetter: BookInformationGetter,
) {
    fun search(title: String): List<BookInformationResponse> {
        return bookInformationGetter.search(title)
            .map(::BookInformationResponse)
    }
}
