package org.debooklog.debooklogserver.adapter.persistence.book

import org.springframework.data.jpa.repository.JpaRepository

interface LikeJpaRepository : JpaRepository<LikeEntity, Long> {
    fun existsByBookIdAndMemberId(
        bookId: Long,
        memberId: Long,
    ): Boolean

    fun findByBookIdAndMemberId(
        bookId: Long,
        memberId: Long,
    ): LikeEntity?
}
