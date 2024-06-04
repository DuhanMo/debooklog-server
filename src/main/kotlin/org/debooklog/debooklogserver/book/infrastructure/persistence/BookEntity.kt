package org.debooklog.debooklogserver.book.infrastructure.persistence

import jakarta.persistence.Column
import jakarta.persistence.Convert
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType.IDENTITY
import jakarta.persistence.Id
import jakarta.persistence.Table
import org.debooklog.debooklogserver.book.domain.Book
import org.debooklog.debooklogserver.common.domain.BaseEntity
import org.debooklog.debooklogserver.common.domain.StringListConverter
import org.hibernate.annotations.SQLRestriction
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import java.time.LocalDateTime

@SQLRestriction("is_deleted = false")
@Entity
@Table(name = "books")
class BookEntity(
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id")
    val id: Long? = null,
    @Column(name = "member_id")
    val memberId: Long,
    @Column(name = "bookshelf_id")
    val bookshelfId: Long,
    @Column(name = "title")
    val title: String,
    @Column(name = "author")
    val author: String,
    @Column(name = "isbn")
    @Convert(converter = StringListConverter::class)
    val isbn: List<String>,
    @Column(name = "thumbnail")
    val thumbnail: String,
    @Column(name = "like_count")
    val likeCount: Int,
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
            createdAt = createdAt,
            updatedAt = updatedAt,
            deletedAt = deletedAt,
            isDeleted = isDeleted,
        )
    }
}
