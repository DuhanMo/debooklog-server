package org.debooklog.debooklogserver.book.controller.port

import org.debooklog.debooklogserver.book.domain.BookRegisterCommand

interface BookService {
    fun register(command: BookRegisterCommand)

    fun delete(bookId: Long)
}
