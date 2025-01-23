package org.debooklog.adapter.persistence.book

import org.debooklog.core.book.model.Book
import org.debooklog.core.book.port.BookRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Repository

@Repository
class BookRepositoryImpl(
    private val bookJpaRepository: BookJpaRepository,
) : BookRepository {
    override fun findAllByMemberId(memberId: Long): List<Book> {
        return bookJpaRepository.findAllByMemberId(memberId).map(BookEntity::toModel)
    }

    override fun save(book: Book): Book {
        return bookJpaRepository.save(BookEntity(book)).toModel()
    }

    override fun getById(bookId: Long): Book {
        return bookJpaRepository.findByIdOrNull(bookId)
            ?.toModel()
            ?: throw NoSuchElementException()
    }

    override fun findAllByBookshelfId(bookshelfId: Long): List<Book> {
        return bookJpaRepository.findAllByBookshelfId(bookshelfId).map(BookEntity::toModel)
    }

    override fun findAll(): List<Book> {
        return bookJpaRepository.findAll().map(BookEntity::toModel)
    }
}
