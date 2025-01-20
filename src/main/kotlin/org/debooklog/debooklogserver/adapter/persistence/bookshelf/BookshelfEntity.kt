package org.debooklog.debooklogserver.adapter.persistence.bookshelf

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType.IDENTITY
import jakarta.persistence.Id
import jakarta.persistence.Table
import org.debooklog.debooklogserver.common.domain.BaseEntity
import org.debooklog.debooklogserver.core.bookshelf.model.Bookshelf
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import java.time.LocalDateTime

@Entity
@Table(name = "bookshelves")
class BookshelfEntity(
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id")
    val id: Long? = null,
    @Column(name = "member_id")
    val memberId: Long,
    @Column(name = "name")
    val name: String,
    @Column(name = "image_url")
    val imageUrl: String?,
    @CreatedDate
    @Column(name = "created_at")
    var createdAt: LocalDateTime = LocalDateTime.MAX,
    @LastModifiedDate
    @Column(name = "updated_at")
    var updatedAt: LocalDateTime = LocalDateTime.MAX,
    @Column(name = "deleted_at")
    val deletedAt: LocalDateTime?,
    @Column(name = "is_deleted")
    val isDeleted: Boolean,
) : BaseEntity<BookshelfEntity>() {
    constructor(bookshelf: Bookshelf) : this(
        id = bookshelf.id,
        memberId = bookshelf.memberId,
        name = bookshelf.name,
        imageUrl = bookshelf.imageUrl,
        createdAt = bookshelf.createdAt,
        updatedAt = bookshelf.updatedAt,
        deletedAt = bookshelf.deletedAt,
        isDeleted = bookshelf.isDeleted,
    )

    fun toModel(): Bookshelf {
        return Bookshelf(
            id = id,
            memberId = memberId,
            name = name,
            imageUrl = imageUrl,
            createdAt = createdAt,
            updatedAt = updatedAt,
            deletedAt = deletedAt,
            isDeleted = isDeleted,
        )
    }
}
