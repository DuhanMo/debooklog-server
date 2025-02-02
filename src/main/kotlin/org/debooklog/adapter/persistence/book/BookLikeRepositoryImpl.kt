package org.debooklog.adapter.persistence.book

import org.debooklog.core.book.model.BookLike
import org.debooklog.core.bookshelf.port.BookLikeRepository
import org.springframework.stereotype.Repository

@Repository
class BookLikeRepositoryImpl(
    private val bookLikeJpaRepository: BookLikeJpaRepository,
) : BookLikeRepository {
    override fun save(bookLike: BookLike): BookLike {
        return bookLikeJpaRepository.save(BookLikeEntity(bookLike)).toModel()
    }

    override fun existsByBookIdAndMemberId(
        bookId: Long,
        memberId: Long,
    ): Boolean {
        return bookLikeJpaRepository.existsByBookIdAndMemberId(bookId, memberId)
    }

    override fun findByBookIdAndMemberId(
        bookId: Long,
        memberId: Long,
    ): BookLike? {
        return bookLikeJpaRepository.findByBookIdAndMemberId(bookId, memberId)?.toModel()
    }

    override fun findAllByBookIdIn(bookIds: List<Long>): List<BookLike> {
        return bookLikeJpaRepository.findAllByBookIdIn(bookIds).map { it.toModel() }
    }
}
