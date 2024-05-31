package org.debooklog.debooklogserver.book.infrastructure.persistence

import org.springframework.data.jpa.repository.JpaRepository

interface LikeJpaRepository : JpaRepository<LikeEntity, Long> {
    fun existsByBookIdAndMemberId(
        bookId: Long,
        memberId: Long,
    ): Boolean
}
