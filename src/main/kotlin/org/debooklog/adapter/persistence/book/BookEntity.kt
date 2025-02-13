package org.debooklog.adapter.persistence.book

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.EnumType.STRING
import jakarta.persistence.Enumerated
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType.IDENTITY
import jakarta.persistence.Id
import jakarta.persistence.Table
import org.debooklog.adapter.persistence.common.BaseEntity
import org.debooklog.core.book.model.Book
import org.debooklog.core.book.model.BookState
import org.hibernate.annotations.JdbcTypeCode
import org.hibernate.annotations.SQLRestriction
import org.hibernate.type.SqlTypes
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import java.time.Instant

@SQLRestriction("is_deleted = false")
@Entity
@Table(name = "book")
class BookEntity(
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id")
    val id: Long = 0,
    @Column(name = "member_id")
    val memberId: Long,
    @Column(name = "bookshelf_id")
    val bookshelfId: Long,
    @Column(name = "title")
    val title: String,
    @Column(name = "author")
    val author: String,
    @JdbcTypeCode(SqlTypes.JSON)
    @Column(name = "isbn", columnDefinition = "json")
    val isbn: List<String>,
    @Column(name = "thumbnail")
    val thumbnail: String,
    @Column(name = "like_count")
    val likeCount: Int,
    @Enumerated(STRING)
    @Column(name = "state")
    val state: BookState,
    @CreatedDate
    @Column(name = "created_at")
    var createdAt: Instant = Instant.MAX,
    @LastModifiedDate
    @Column(name = "updated_at")
    var updatedAt: Instant = Instant.MAX,
    @Column(name = "deleted_at")
    val deletedAt: Instant?,
    @Column(name = "is_deleted")
    val isDeleted: Boolean,
) : BaseEntity<BookEntity>() {
    constructor(book: Book) : this(
        id = book.id,
        memberId = book.memberId,
        bookshelfId = book.bookshelfId,
        title = book.title,
        author = book.author,
        isbn = book.isbn,
        thumbnail = book.thumbnail,
        likeCount = book.likeCount,
        state = book.state,
        createdAt = book.createdAt,
        updatedAt = book.updatedAt,
        deletedAt = book.deletedAt,
        isDeleted = book.isDeleted,
    )

    fun toModel(): Book {
        return Book(
            id = id,
            memberId = memberId,
            bookshelfId = bookshelfId,
            title = title,
            author = author,
            isbn = isbn,
            thumbnail = thumbnail,
            likeCount = likeCount,
            state = state,
            createdAt = createdAt,
            updatedAt = updatedAt,
            deletedAt = deletedAt,
            isDeleted = isDeleted,
        )
    }
}
