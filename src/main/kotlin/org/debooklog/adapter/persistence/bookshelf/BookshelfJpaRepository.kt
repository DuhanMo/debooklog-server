package org.debooklog.adapter.persistence.bookshelf

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface BookshelfJpaRepository : JpaRepository<BookshelfEntity, Long> {
    fun findAllByDeletedAtIsNull(): List<BookshelfEntity>

    fun findByMemberId(memberId: Long): BookshelfEntity?

    @Query(
        """
            SELECT b FROM BookshelfEntity b
            LEFT JOIN BookEntity bk ON b.id = bk.bookshelfId AND bk.isDeleted = false
            WHERE b.isDeleted = false
            GROUP BY b.id
            ORDER BY COALESCE(MAX(bk.createdAt), b.updatedAt) DESC
        """,
    )
    fun findBookshelvesSortedByLatestActivity(): List<BookshelfEntity>
}
