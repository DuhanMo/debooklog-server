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
        memberId: Long,
    ) {
        val bookshelf = bookshelfRepository.getById(bookshelfId)
        bookshelfRepository.save(bookshelf.update(name, memberId))
    }
}
