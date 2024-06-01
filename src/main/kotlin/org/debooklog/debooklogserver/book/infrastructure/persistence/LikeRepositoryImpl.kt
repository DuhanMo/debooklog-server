package org.debooklog.debooklogserver.book.infrastructure.persistence

import org.debooklog.debooklogserver.book.domain.Like
import org.debooklog.debooklogserver.bookshelf.service.port.LikeRepository
import org.springframework.stereotype.Repository

@Repository
class LikeRepositoryImpl(
    private val likeJpaRepository: LikeJpaRepository,
) : LikeRepository {
    override fun save(like: Like): Like {
        return likeJpaRepository.save(LikeEntity.from(like)).toModel()
    }

    override fun existsByBookIdAndMemberId(
        bookId: Long,
        memberId: Long,
    ): Boolean {
        return likeJpaRepository.existsByBookIdAndMemberId(bookId, memberId)
    }

    override fun findByBookIdAndMemberId(
        bookId: Long,
        memberId: Long,
    ): Like? {
        return likeJpaRepository.findByBookIdAndMemberId(bookId, memberId)?.toModel()
    }
}
