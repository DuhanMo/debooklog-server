package org.debooklog.debooklogserver.book.service

import org.assertj.core.api.Assertions.assertThat
import org.debooklog.debooklogserver.book.domain.BookRegisterCommand
import org.debooklog.debooklogserver.book.mock.FakeBookRepository
import org.junit.jupiter.api.Test

class BookServiceTest {
    private lateinit var bookService: BookServiceImpl

    @Test
    fun `BookRegisterCommand를 통해 책을 저장하는 경우 책이 저장된다`() {
        // given
        val fakeBookRepository = FakeBookRepository()
        bookService = BookServiceImpl(fakeBookRepository)
        // when
        bookService.register(
            BookRegisterCommand(
                memberId = 1L,
                bookshelfId = 1L,
                title = "title",
                author = "author",
                isbn = listOf("111111111"),
                thumbnail = "thumbnail",
            ),
        )
        // then
        val books = fakeBookRepository.findAllByMemberId(1L)
        assertThat(books.size).isEqualTo(1)
        assertThat(books.first().title).isEqualTo("title")
    }
}
