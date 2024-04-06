package org.debooklog.debooklogserver.book.service

import org.debooklog.debooklogserver.book.domain.Book
import org.debooklog.debooklogserver.book.domain.BookRepository
import org.debooklog.debooklogserver.book.service.dto.BookRegisterCommand
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class BookService(
    private val bookRepository: BookRepository,
) {
    fun register(command: BookRegisterCommand) {
        if (isAlreadySavedBook(command)) {
            throw IllegalArgumentException("이미 저장한 책입니다!")
        }
        bookRepository.save(
            Book(
                memberId = command.memberId,
                title = command.title,
                author = command.author,
                isbn = command.isbn,
                thumbnail = command.thumbnail,
            ),
        )
    }

    private fun isAlreadySavedBook(command: BookRegisterCommand): Boolean {
        val books = bookRepository.findAllByMemberId(command.memberId)
        return books.flatMap { it.isbn }.intersect(command.isbn.toSet()).isNotEmpty()
    }
}
