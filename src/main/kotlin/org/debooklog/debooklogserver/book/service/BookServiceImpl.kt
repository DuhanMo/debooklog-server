package org.debooklog.debooklogserver.book.service

import org.debooklog.debooklogserver.book.controller.port.BookService
import org.debooklog.debooklogserver.book.domain.Book
import org.debooklog.debooklogserver.book.domain.BookRegisterCommand
import org.debooklog.debooklogserver.book.service.port.BookRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class BookServiceImpl(
    private val bookRepository: BookRepository,
) : BookService {
    override fun register(command: BookRegisterCommand) {
        val books = bookRepository.findAllByMemberId(command.memberId)
        val book = Book.from(command)
        book.validateForDuplicate(books)
        bookRepository.save(book)
    }
}
