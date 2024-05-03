package org.debooklog.debooklogserver.bookshelf.domain

import org.debooklog.debooklogserver.bookshelf.service.port.BookShelfNameGenerator
import java.time.LocalDateTime

class BookShelf(
    val id: Long?,
    val memberId: Long,
    val name: String,
    val createdAt: LocalDateTime = LocalDateTime.MAX,
    val updatedAt: LocalDateTime = LocalDateTime.MAX,
    val deletedAt: LocalDateTime?,
) {
    constructor(memberId: Long, bookShelfNameGenerator: BookShelfNameGenerator) : this(
        id = null,
        memberId = memberId,
        name = bookShelfNameGenerator.generate(),
        deletedAt = null,
    )

    fun update(name: String): BookShelf {
        return BookShelf(
            id = id,
            memberId = memberId,
            name = name,
            deletedAt = null,
        )
    }
}
