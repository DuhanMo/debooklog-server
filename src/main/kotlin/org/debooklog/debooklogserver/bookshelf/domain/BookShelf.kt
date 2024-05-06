package org.debooklog.debooklogserver.bookshelf.domain

import java.time.LocalDateTime
import java.time.LocalDateTime.now

class BookShelf(
    val id: Long?,
    val memberId: Long,
    val name: String,
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime,
    val deletedAt: LocalDateTime?,
) {
    constructor(memberId: Long, name: String, createdAt: LocalDateTime) : this(
        id = null,
        memberId = memberId,
        name = name,
        createdAt = now(),
        updatedAt = now(),
        deletedAt = null,
    )

    fun update(name: String): BookShelf {
        return BookShelf(
            id = id,
            memberId = memberId,
            name = name,
            createdAt = createdAt,
            updatedAt = now(),
            deletedAt = null,
        )
    }
}
