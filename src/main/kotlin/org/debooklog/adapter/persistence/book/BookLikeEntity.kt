package org.debooklog.adapter.persistence.book

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType.IDENTITY
import jakarta.persistence.Id
import jakarta.persistence.Table
import org.debooklog.core.book.model.BookLike
import org.hibernate.annotations.SQLRestriction
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import java.time.Instant

@SQLRestriction("is_deleted = false")
@Entity
@Table(name = "book_like")
class BookLikeEntity(
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id")
    val id: Long = 0,
    @Column(name = "book_id")
    val bookId: Long,
    @Column(name = "member_id")
    val memberId: Long,
    @CreatedDate
    @Column(name = "created_at")
    var createdAt: Instant,
    @LastModifiedDate
    @Column(name = "updated_at")
    var updatedAt: Instant,
    @Column(name = "deleted_at")
    val deletedAt: Instant?,
    @Column(name = "is_deleted")
    val isDeleted: Boolean,
) {
    constructor(bookLike: BookLike) : this(
        id = bookLike.id,
        bookId = bookLike.bookId,
        memberId = bookLike.memberId,
        createdAt = bookLike.createdAt,
        updatedAt = bookLike.updatedAt,
        deletedAt = bookLike.deletedAt,
        isDeleted = bookLike.isDeleted,
    )

    fun toModel(): BookLike {
        return BookLike(
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
