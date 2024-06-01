package org.debooklog.debooklogserver.book.controller.port

interface LikeService {
    fun create(
        bookId: Long,
        memberId: Long,
    )

    fun remove(
        bookId: Long,
        memberId: Long,
    )
}
