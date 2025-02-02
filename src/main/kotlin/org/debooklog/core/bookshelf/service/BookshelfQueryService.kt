package org.debooklog.core.bookshelf.service

import org.debooklog.core.book.port.BookRepository
import org.debooklog.core.bookshelf.model.Bookshelf
import org.debooklog.core.bookshelf.port.BookLikeRepository
import org.debooklog.core.bookshelf.port.BookshelfRepository
import org.debooklog.core.member.port.MemberRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class BookshelfQueryService(
    private val memberRepository: MemberRepository,
    private val bookshelfRepository: BookshelfRepository,
    private val bookRepository: BookRepository,
    private val bookLikeRepository: BookLikeRepository,
) {
    fun findAll(): List<Bookshelf> {
        return bookshelfRepository.findAll()
    }

    fun find(bookshelfId: Long): BookshelfWithBooks {
        val bookshelf = bookshelfRepository.getById(bookshelfId)
        val member = memberRepository.getById(bookshelf.memberId)
        val books = bookRepository.findAllByBookshelfId(bookshelfId)
        val bookLikes = bookLikeRepository.findAllByBookIdIn(books.map { it.id })
        return BookshelfWithBooks(bookshelf, member, books, bookLikes)
    }
}
