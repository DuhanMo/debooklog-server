package org.debooklog.debooklogserver.bookshelf.service

import org.debooklog.debooklogserver.bookshelf.controller.port.BookShelfService
import org.debooklog.debooklogserver.bookshelf.domain.BookShelf
import org.debooklog.debooklogserver.bookshelf.service.port.BookShelfNameGenerator
import org.debooklog.debooklogserver.bookshelf.service.port.BookShelfRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class BookShelfServiceImpl(
    private val bookShelfRepository: BookShelfRepository,
    private val bookShelfNameGenerator: BookShelfNameGenerator,
) : BookShelfService {
    override fun create(memberId: Long) {
        val bookShelf =
            BookShelf(
                memberId = memberId,
                bookShelfNameGenerator = bookShelfNameGenerator,
            )
        bookShelfRepository.save(bookShelf)
    }
}
