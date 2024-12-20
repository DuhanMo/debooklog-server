package org.debooklog.debooklogserver.book.infrastructure.persistence

import org.springframework.data.jpa.repository.JpaRepository

interface BookJpaRepository : JpaRepository<BookEntity, Long> {
    fun findAllByMemberId(memberId: Long): List<BookEntity>

    fun findAllByBookshelfId(bookshelfId: Long): List<BookEntity>
}
