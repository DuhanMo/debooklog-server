package org.debooklog.debooklogserver.bookshelf.infrastructure.persistence

import org.debooklog.debooklogserver.bookshelf.domain.BookShelf
import org.debooklog.debooklogserver.bookshelf.service.port.BookShelfRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Repository

@Repository
class BookShelfRepositoryImpl(
    private val bookShelfJpaRepository: BookShelfJpaRepository,
) : BookShelfRepository {
    override fun save(bookShelf: BookShelf): BookShelf {
        return bookShelfJpaRepository.save(BookShelfEntity.from(bookShelf)).toModel()
    }

    override fun getById(id: Long): BookShelf {
        return bookShelfJpaRepository.findByIdOrNull(id)
            ?.toModel()
            ?: throw NoSuchElementException()
    }

    override fun findAll(): List<BookShelf> {
        return bookShelfJpaRepository.findAllByDeletedAtIsNull().map { it.toModel() }
    }
}
