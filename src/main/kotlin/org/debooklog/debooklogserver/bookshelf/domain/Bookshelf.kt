package org.debooklog.debooklogserver.bookshelf.domain

import java.time.LocalDateTime
import java.time.LocalDateTime.now

data class Bookshelf(
    val id: Long?,
    val memberId: Long,
    val name: String,
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime,
    val deletedAt: LocalDateTime?,
    val isDeleted: Boolean,
) {
    constructor(memberId: Long, name: String, now: LocalDateTime) : this(
        id = null,
        memberId = memberId,
        name = name,
        createdAt = now,
        updatedAt = now,
        deletedAt = null,
        isDeleted = false,
    )

    fun update(name: String): Bookshelf {
        return this.copy(name = name, updatedAt = now())
    }
}
