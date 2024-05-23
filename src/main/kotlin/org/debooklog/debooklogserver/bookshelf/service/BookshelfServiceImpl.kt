package org.debooklog.debooklogserver.bookshelf.service

import org.debooklog.debooklogserver.bookshelf.controller.port.BookshelfService
import org.debooklog.debooklogserver.bookshelf.service.port.BookshelfRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class BookshelfServiceImpl(
    private val bookshelfRepository: BookshelfRepository,
) : BookshelfService {
    @Transactional
    override fun update(
        bookshelfId: Long,
        name: String,
    ) {
        val bookshelf = bookshelfRepository.getById(bookshelfId)
        // todo 자신의 책장만 수정 가능하도록 검증
        bookshelfRepository.save(bookshelf.update(name))
    }
}
