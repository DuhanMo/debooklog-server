package org.debooklog.debooklogserver.bookshelf.infrastructure.persistence

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Table
import java.time.LocalDateTime
import org.debooklog.debooklogserver.bookshelf.domain.BookShelf
import org.debooklog.debooklogserver.common.domain.BaseEntity

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
            deletedAt = deletedAt
        )
    }
}
