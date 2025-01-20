package org.debooklog.debooklogserver.core.bookshelf.service

import org.debooklog.debooklogserver.core.bookshelf.port.BookshelfRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class BookshelfService(
    private val bookshelfRepository: BookshelfRepository,
) {
    @Transactional
    fun update(
        bookshelfId: Long,
        name: String,
        memberId: Long,
    ) {
        val bookshelf = bookshelfRepository.getById(bookshelfId)
        bookshelfRepository.save(bookshelf.update(name, memberId))
    }
}
