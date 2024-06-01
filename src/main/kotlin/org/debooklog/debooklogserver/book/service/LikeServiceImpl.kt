package org.debooklog.debooklogserver.book.service

import org.debooklog.debooklogserver.book.controller.port.LikeService
import org.debooklog.debooklogserver.book.domain.Like
import org.debooklog.debooklogserver.book.service.port.BookRepository
import org.debooklog.debooklogserver.bookshelf.service.port.LikeRepository
import org.springframework.stereotype.Service

@Service
class LikeServiceImpl(
    private val bookRepository: BookRepository,
    private val likeRepository: LikeRepository,
) : LikeService {
    override fun create(
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

    override fun remove(
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
