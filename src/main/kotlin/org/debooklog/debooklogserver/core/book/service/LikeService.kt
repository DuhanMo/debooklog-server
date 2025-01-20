package org.debooklog.debooklogserver.core.book.service

import org.debooklog.debooklogserver.bookshelf.service.port.LikeRepository
import org.debooklog.debooklogserver.core.book.model.Like
import org.debooklog.debooklogserver.core.book.port.BookRepository
import org.springframework.stereotype.Service

@Service
class LikeService(
    private val bookRepository: BookRepository,
    private val likeRepository: LikeRepository,
) {
    fun create(
        bookId: Long,
        memberId: Long,
    ) {
        if (likeRepository.existsByBookIdAndMemberId(bookId, memberId)) {
            return
        }
        val book = bookRepository.getById(bookId)
        bookRepository.save(book.increaseLikeCount())

        likeRepository.save(Like(bookId = bookId, memberId = memberId))
    }

    fun remove(
        bookId: Long,
        memberId: Long,
    ) {
        if (!likeRepository.existsByBookIdAndMemberId(bookId, memberId)) {
            return
        }
        val book = bookRepository.getById(bookId)
        bookRepository.save(book.decreaseLikeCount())

        val like = likeRepository.findByBookIdAndMemberId(bookId, memberId) ?: return
        likeRepository.save(like.delete())
    }
}
