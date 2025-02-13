package org.debooklog.core.book.service

import org.debooklog.core.book.model.BookLike
import org.debooklog.core.book.port.BookRepository
import org.debooklog.core.bookshelf.port.BookLikeRepository
import org.springframework.stereotype.Service
import java.time.Instant.now

@Service
class BookLikeService(
    private val bookRepository: BookRepository,
    private val bookLikeRepository: BookLikeRepository,
) {
    fun create(
        bookId: Long,
        memberId: Long,
    ) {
        val now = now()
        if (bookLikeRepository.existsByBookIdAndMemberId(bookId, memberId)) {
            return
        }
        val book = bookRepository.getById(bookId)
        bookRepository.save(book.increaseLikeCount())

        bookLikeRepository.save(BookLike(bookId = bookId, memberId = memberId, now = now))
    }

    fun remove(
        bookId: Long,
        memberId: Long,
    ) {
        if (!bookLikeRepository.existsByBookIdAndMemberId(bookId, memberId)) {
            return
        }
        val book = bookRepository.getById(bookId)
        bookRepository.save(book.decreaseLikeCount())

        val like = bookLikeRepository.findByBookIdAndMemberId(bookId, memberId) ?: return
        bookLikeRepository.save(like.delete())
    }
}
