package org.debooklog.debooklogserver.bookshelf.infrastructure.persistence

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Table
import org.debooklog.debooklogserver.bookshelf.domain.Bookshelf
import org.debooklog.debooklogserver.common.domain.BaseEntity
import java.time.LocalDateTime

@Entity
@Table(name = "bookshelves")
class BookshelfEntity(
    @Column(name = "member_id")
    val memberId: Long,
    @Column(name = "name")
    val name: String,
    @Column(name = "deleted_at")
    val deletedAt: LocalDateTime?,
) : BaseEntity<BookshelfEntity>() {
    fun toModel(): Bookshelf {
        return Bookshelf(
            id = id,
            memberId = memberId,
            name = name,
            createdAt = createdAt,
            updatedAt = updatedAt,
            deletedAt = deletedAt,
        )
    }

    companion object {
        fun from(bookshelf: Bookshelf): BookshelfEntity =
            BookshelfEntity(
                memberId = bookshelf.memberId,
                name = bookshelf.name,
                deletedAt = bookshelf.deletedAt,
            )
    }
}
