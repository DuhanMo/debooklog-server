package org.debooklog.debooklogserver.adapter.persistence.book

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType.IDENTITY
import jakarta.persistence.Id
import jakarta.persistence.Table
import org.debooklog.debooklogserver.core.book.model.Like
import org.hibernate.annotations.SQLRestriction
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import java.time.LocalDateTime

@SQLRestriction("is_deleted = false")
@Entity
@Table(name = "likes")
class LikeEntity(
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id")
    val id: Long? = null,
    @Column(name = "book_id")
    val bookId: Long,
    @Column(name = "member_id")
    val memberId: Long,
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
) {
    constructor(like: Like) : this(
        id = like.id,
        bookId = like.bookId,
        memberId = like.memberId,
        createdAt = like.createdAt,
        updatedAt = like.updatedAt,
        deletedAt = like.deletedAt,
        isDeleted = like.isDeleted,
    )

    fun toModel(): Like {
        return Like(
            id = id,
            bookId = bookId,
            memberId = memberId,
            createdAt = createdAt,
            updatedAt = updatedAt,
            deletedAt = deletedAt,
            isDeleted = isDeleted,
        )
    }
}
