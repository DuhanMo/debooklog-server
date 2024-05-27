package org.debooklog.debooklogserver.bookshelf.infrastructure.persistence

import org.springframework.data.jpa.repository.JpaRepository

interface BookshelfJpaRepository : JpaRepository<BookshelfEntity, Long> {
    fun findAllByDeletedAtIsNull(): List<BookshelfEntity>

    fun findByMemberId(memberId: Long): BookshelfEntity?
}
