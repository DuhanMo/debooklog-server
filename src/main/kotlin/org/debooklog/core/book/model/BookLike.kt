package org.debooklog.core.book.model

import java.time.Instant
import java.time.Instant.now

data class BookLike(
    val id: Long,
    val bookId: Long,
    val memberId: Long,
    val createdAt: Instant,
    val updatedAt: Instant,
    val deletedAt: Instant?,
    val isDeleted: Boolean,
) {
    fun delete(): BookLike {
        return this.copy(updatedAt = now(), deletedAt = now(), isDeleted = true)
    }

    constructor(bookId: Long, memberId: Long, now: Instant) : this(
        id = 0,
        bookId = bookId,
        memberId = memberId,
        createdAt = now,
        updatedAt = now,
        deletedAt = null,
        isDeleted = false,
    )
}
