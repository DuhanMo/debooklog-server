package org.debooklog.debooklogserver.bookshelf.service.port

import org.debooklog.debooklogserver.core.book.model.Like

interface LikeRepository {
    fun save(like: Like): Like

    fun existsByBookIdAndMemberId(
        bookId: Long,
        memberId: Long,
    ): Boolean

    fun findByBookIdAndMemberId(
        bookId: Long,
        memberId: Long,
    ): Like?
}
