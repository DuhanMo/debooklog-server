package org.debooklog.debooklogserver.bookshelf.infrastructure.persistence

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Table
import org.debooklog.debooklogserver.bookshelf.domain.BookShelf
import org.debooklog.debooklogserver.common.domain.BaseEntity

@Entity
@Table(name = "bookshelfs")
class BookShelfEntity(
    @Column(name = "member_id")
    val memberId: Long,
    @Column(name = "name")
    val name: String,
) : BaseEntity() {
    companion object {
        fun from(bookShelf: BookShelf): BookShelfEntity =
            BookShelfEntity(
                memberId = bookShelf.memberId,
                name = bookShelf.name,
            )
    }

    fun toModel(): BookShelf {
        return BookShelf(
            id = id,
            memberId = memberId,
            name = name,
            createdAt = createdAt,
            updatedAt = updatedAt,
        )
    }
}
