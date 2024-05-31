package org.debooklog.debooklogserver.book.domain

import java.time.LocalDateTime

data class Like(
    val id: Long?,
    val bookId: Long,
    val memberId: Long,
    val createdAt: LocalDateTime = LocalDateTime.MAX,
    val updatedAt: LocalDateTime = LocalDateTime.MAX,
    val deletedAt: LocalDateTime?,
    val isDeleted: Boolean,
) {
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
