package org.debooklog.debooklogserver.bookshelf.service

import org.debooklog.debooklogserver.bookshelf.controller.port.BookShelfQueryService
import org.debooklog.debooklogserver.bookshelf.domain.BookShelf
import org.debooklog.debooklogserver.bookshelf.service.port.BookShelfRepository

class BookShelfQueryServiceImpl(
    private val bookShelfRepository: BookShelfRepository,
) : BookShelfQueryService {
    override fun findAll(): List<BookShelf> {
        return bookShelfRepository.findAll()
    }
}
