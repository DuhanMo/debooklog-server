package org.debooklog.debooklogserver.bookshelf.service

import org.debooklog.debooklogserver.bookshelf.controller.port.BookShelfQueryService
import org.debooklog.debooklogserver.bookshelf.domain.BookShelf
import org.debooklog.debooklogserver.bookshelf.service.port.BookShelfRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class BookShelfQueryServiceImpl(
    private val bookShelfRepository: BookShelfRepository,
) : BookShelfQueryService {
    override fun findAll(): List<BookShelf> {
        return bookShelfRepository.findAll()
    }
}
