package org.debooklog.core.book.model

import java.time.LocalDateTime
import java.time.LocalDateTime.now

data class BookLike(
    val id: Long,
    val bookId: Long,
    val memberId: Long,
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime,
    val deletedAt: LocalDateTime?,
    val isDeleted: Boolean,
) {
    fun delete(): BookLike {
        return this.copy(updatedAt = now(), deletedAt = now(), isDeleted = true)
    }

    constructor(bookId: Long, memberId: Long, now: LocalDateTime) : this(
        id = 0,
        bookId = bookId,
        memberId = memberId,
        createdAt = now,
        updatedAt = now,
        deletedAt = null,
        isDeleted = false,
    )
}
