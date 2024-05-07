package org.debooklog.debooklogserver.bookshelf.service

import org.debooklog.debooklogserver.bookshelf.controller.port.BookshelfQueryService
import org.debooklog.debooklogserver.bookshelf.domain.Bookshelf
import org.debooklog.debooklogserver.bookshelf.service.port.BookshelfRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class BookshelfQueryServiceImpl(
    private val bookshelfRepository: BookshelfRepository,
) : BookshelfQueryService {
    override fun findAll(): List<Bookshelf> {
        return bookshelfRepository.findAll()
    }
}
