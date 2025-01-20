package org.debooklog.debooklogserver.adapter.persistence.bookshelf

import org.springframework.data.jpa.repository.JpaRepository

interface BookshelfJpaRepository : JpaRepository<BookshelfEntity, Long> {
    fun findAllByDeletedAtIsNull(): List<BookshelfEntity>

    fun findByMemberId(memberId: Long): BookshelfEntity?
}
