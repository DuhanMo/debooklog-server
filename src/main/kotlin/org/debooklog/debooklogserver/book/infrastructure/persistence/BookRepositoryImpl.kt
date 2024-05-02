package org.debooklog.debooklogserver.book.infrastructure.persistence

import org.debooklog.debooklogserver.book.domain.Book
import org.debooklog.debooklogserver.book.service.port.BookRepository
import org.springframework.stereotype.Repository

@Repository
class BookRepositoryImpl(
    private val bookJpaRepository: BookJpaRepository,
) : BookRepository {
    override fun findAllByMemberId(memberId: Long): List<Book> {
        return bookJpaRepository.findAllByMemberId(memberId).map(BookEntity::toModel)
    }

    override fun save(book: Book): Book {
        return bookJpaRepository.save(BookEntity.from(book)).toModel()
    }
}