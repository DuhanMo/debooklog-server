package org.debooklog.debooklogserver.bookshelf.infrastructure.persistence

import org.debooklog.debooklogserver.bookshelf.domain.Bookshelf
import org.debooklog.debooklogserver.bookshelf.service.port.BookshelfRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Repository

@Repository
class BookshelfRepositoryImpl(
    private val bookshelfJpaRepository: BookshelfJpaRepository,
) : BookshelfRepository {
    override fun save(bookshelf: Bookshelf): Bookshelf {
        return bookshelfJpaRepository.save(BookshelfEntity(bookshelf)).toModel()
    }

    override fun getById(id: Long): Bookshelf {
        return bookshelfJpaRepository.findByIdOrNull(id)
            ?.toModel()
            ?: throw NoSuchElementException()
    }

    override fun findAll(): List<Bookshelf> {
        return bookshelfJpaRepository.findAllByDeletedAtIsNull().map { it.toModel() }
    }

    override fun findByMemberId(memberId: Long): Bookshelf? {
        return bookshelfJpaRepository.findByMemberId(memberId)?.toModel()
    }
}
