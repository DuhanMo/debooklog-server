package org.debooklog.adapter.persistence.book

import org.springframework.data.jpa.repository.JpaRepository

interface BookLikeJpaRepository : JpaRepository<BookLikeEntity, Long> {
    fun existsByBookIdAndMemberId(
        bookId: Long,
        memberId: Long,
    ): Boolean

    fun findByBookIdAndMemberId(
        bookId: Long,
        memberId: Long,
    ): BookLikeEntity?

    fun findAllByBookIdIn(bookIds: List<Long>): List<BookLikeEntity>
}
