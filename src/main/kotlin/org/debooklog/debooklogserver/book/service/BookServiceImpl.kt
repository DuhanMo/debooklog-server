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
        verifyBook(command)
        bookRepository.save(Book.from(command))
    }

    private fun verifyBook(command: BookRegisterCommand) {
        if (isAlreadySavedBook(command)) {
            throw IllegalArgumentException("이미 저장한 책입니다!")
        }
    }

    private fun isAlreadySavedBook(command: BookRegisterCommand): Boolean {
        val books = bookRepository.findAllByMemberId(command.memberId)
        return books.flatMap { it.isbn }.intersect(command.isbn.toSet()).isNotEmpty()
    }q
}
