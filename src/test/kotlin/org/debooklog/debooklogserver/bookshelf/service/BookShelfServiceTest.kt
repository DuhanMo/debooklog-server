package org.debooklog.debooklogserver.bookshelf.service

import org.assertj.core.api.Assertions.assertThat
import org.debooklog.debooklogserver.book.mock.FakeBookShelfNameGenerator
import org.debooklog.debooklogserver.bookshelf.mock.FakeBookShelfRepository
import org.junit.jupiter.api.Test

class BookShelfServiceTest {
    private lateinit var bookShelfService: BookShelfServiceImpl

    @Test
    fun `BookShelf 를 저장할 수 있다`() {
        // given
        val fakeBookShelfNameGenerator = FakeBookShelfNameGenerator("책장이름")
        val fakeBookShelfRepository = FakeBookShelfRepository()
        bookShelfService = BookShelfServiceImpl(fakeBookShelfRepository, fakeBookShelfNameGenerator)
        // when
        bookShelfService.create(memberId = 20L)
        // then
        val savedBookShelf = fakeBookShelfRepository.getById(1L)
        assertThat(savedBookShelf.name).isEqualTo("책장이름")
        assertThat(savedBookShelf.memberId).isEqualTo(20L)
    }
}
