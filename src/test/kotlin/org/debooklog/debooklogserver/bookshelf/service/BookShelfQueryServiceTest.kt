package org.debooklog.debooklogserver.bookshelf.service

import org.assertj.core.api.Assertions.assertThat
import org.debooklog.debooklogserver.bookshelf.domain.BookShelf
import org.debooklog.debooklogserver.bookshelf.mock.FakeBookShelfRepository
import org.junit.jupiter.api.Test
import java.time.LocalDateTime

class BookShelfQueryServiceTest {
    private lateinit var bookShelfQueryService: BookShelfQueryServiceImpl

    @Test
    fun `findAll 은 삭제되지 않은 모든 BookShelf 를 조회한다`() {
        // given
        val fakeBookShelfRepository = FakeBookShelfRepository()
        fakeBookShelfRepository.save(
            BookShelf(
                id = 1L,
                memberId = 1L,
                "책장1",
                deletedAt = null,
            ),
        )
        fakeBookShelfRepository.save(
            BookShelf(
                id = 2L,
                memberId = 2L,
                "책장2",
                deletedAt = null,
            ),
        )
        fakeBookShelfRepository.save(
            BookShelf(
                id = 3L,
                memberId = 3L,
                "책장3",
                deletedAt = LocalDateTime.now(),
            ),
        )
        bookShelfQueryService = BookShelfQueryServiceImpl(fakeBookShelfRepository)
        // when
        val bookShelves = bookShelfQueryService.findAll()
        // then
        assertThat(bookShelves.size).isEqualTo(2)
    }
}
