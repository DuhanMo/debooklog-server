package org.debooklog.core.book.model

import java.time.LocalDateTime
import java.time.LocalDateTime.now

data class BookLike(
    val id: Long?,
    val bookId: Long,
    val memberId: Long,
    val createdAt: LocalDateTime = LocalDateTime.MAX,
    val updatedAt: LocalDateTime = LocalDateTime.MAX,
    val deletedAt: LocalDateTime?,
    val isDeleted: Boolean,
) {
    fun delete(): BookLike {
        return this.copy(deletedAt = now(), isDeleted = true)
    }

    constructor(bookId: Long, memberId: Long) : this(
        id = null,
        bookId = bookId,
        memberId = memberId,
        createdAt = LocalDateTime.MAX,
        updatedAt = LocalDateTime.MAX,
        deletedAt = null,
        isDeleted = false,
    )
}
