package org.debooklog.core.bookshelf.port

import org.debooklog.core.book.model.BookLike

interface BookLikeRepository {
    fun save(bookLike: BookLike): BookLike

    fun existsByBookIdAndMemberId(
        bookId: Long,
        memberId: Long,
    ): Boolean

    fun findByBookIdAndMemberId(
        bookId: Long,
        memberId: Long,
    ): BookLike?

    fun findAllByBookIdIn(bookIds: List<Long>): List<BookLike>
}
