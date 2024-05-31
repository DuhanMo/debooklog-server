package org.debooklog.debooklogserver.bookshelf.service.port

import org.debooklog.debooklogserver.book.domain.Like

interface LikeRepository {
    fun save(like: Like): Like

    fun existsByBookIdAndMemberId(
        bookId: Long,
        memberId: Long,
    ): Boolean
}
