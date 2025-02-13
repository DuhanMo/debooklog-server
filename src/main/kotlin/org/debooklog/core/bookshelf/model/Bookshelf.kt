package org.debooklog.core.bookshelf.model

import java.time.Instant
import java.time.Instant.now

data class Bookshelf(
    val id: Long,
    val memberId: Long,
    val name: String,
    val imageUrl: String?,
    val createdAt: Instant,
    val updatedAt: Instant,
    val deletedAt: Instant?,
    val isDeleted: Boolean,
) {
    constructor(memberId: Long, name: String, imageUrl: String?, now: Instant) : this(
        id = 0,
        memberId = memberId,
        name = name,
        imageUrl = imageUrl,
        createdAt = now,
        updatedAt = now,
        deletedAt = null,
        isDeleted = false,
    )

    fun update(
        name: String,
        memberId: Long,
    ): Bookshelf {
        if (this.memberId != memberId) {
            throw IllegalArgumentException("권한이 없습니다")
        }
        return this.copy(name = name, updatedAt = now())
    }
}
