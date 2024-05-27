package org.debooklog.debooklogserver.book.service

import org.debooklog.debooklogserver.book.controller.port.BookService
import org.debooklog.debooklogserver.book.domain.Book
import org.debooklog.debooklogserver.book.domain.BookRegisterCommand
import org.debooklog.debooklogserver.book.service.port.BookRepository
import org.debooklog.debooklogserver.bookshelf.service.port.BookshelfRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class BookServiceImpl(
    private val bookRepository: BookRepository,
    private val bookshelfRepository: BookshelfRepository,
) : BookService {
    override fun register(command: BookRegisterCommand) {
        val books = bookRepository.findAllByMemberId(command.memberId)
        val bookshelf =
            bookshelfRepository.findByMemberId(command.memberId)
                ?: throw NoSuchElementException("책장을 찾지 못했습니다")
        val book = Book.from(command, bookshelf.id!!)
        book.validateForDuplicate(books)
        bookRepository.save(book)
    }

    override fun delete(
        bookId: Long,
        memberId: Long,
    ) {
        val book = bookRepository.getById(bookId)
        bookRepository.save(book.delete(memberId))
    }
}
