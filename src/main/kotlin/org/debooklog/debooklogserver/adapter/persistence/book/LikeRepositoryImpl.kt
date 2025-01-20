package org.debooklog.debooklogserver.adapter.persistence.book

import org.debooklog.debooklogserver.bookshelf.service.port.LikeRepository
import org.debooklog.debooklogserver.core.book.model.Like
import org.springframework.stereotype.Repository

@Repository
class LikeRepositoryImpl(
    private val likeJpaRepository: LikeJpaRepository,
) : LikeRepository {
    override fun save(like: Like): Like {
        return likeJpaRepository.save(LikeEntity(like)).toModel()
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
