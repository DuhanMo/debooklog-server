package org.debooklog.debooklogserver.book.service

import org.debooklog.debooklogserver.book.controller.port.BookQueryService
import org.debooklog.debooklogserver.book.domain.BookInformationData
import org.debooklog.debooklogserver.book.service.port.BookInformationGetter
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class BookQueryServiceImpl(
    private val bookInformationGetter: BookInformationGetter,
) : BookQueryService {
    override fun search(title: String): List<BookInformationData> {
        return bookInformationGetter.search(title)
    }
}
