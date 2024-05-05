package org.debooklog.debooklogserver.bookshelf.domain

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Table
import org.debooklog.debooklogserver.common.domain.BaseEntity
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

@Entity
@Table(name = "bookshelves")
class BookShelfEntity(
    @Column(name = "member_id")
    val memberId: Long,
    @Column(name = "name")
    val name: String,
    @Column(name = "deleted_at")
    val deletedAt: LocalDateTime?,
) : BaseEntity() {
    companion object {
        fun from(bookShelf: BookShelf): BookShelfEntity =
            BookShelfEntity(
                memberId = bookShelf.memberId,
                name = bookShelf.name,
                deletedAt = bookShelf.deletedAt,
            )
    }

    fun toModel(): BookShelf {
        return BookShelf(
            id = id,
            memberId = memberId,
            name = name,
            createdAt = createdAt,
            updatedAt = updatedAt,
            deletedAt = deletedAt,
        )
    }
}
