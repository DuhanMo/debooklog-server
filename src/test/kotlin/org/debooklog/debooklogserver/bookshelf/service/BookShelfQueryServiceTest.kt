package org.debooklog.debooklogserver.bookshelf.service

import java.time.LocalDateTime
import org.assertj.core.api.Assertions.assertThat
import org.debooklog.debooklogserver.bookshelf.domain.BookShelf
import org.debooklog.debooklogserver.bookshelf.mock.FakeBookShelfRepository
import org.junit.jupiter.api.Test

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
            )
        )
        fakeBookShelfRepository.save(
            BookShelf(
                id = 2L,
                memberId = 2L,
                "책장2",
                deletedAt = null,
            )
        )
        fakeBookShelfRepository.save(
            BookShelf(
                id = 3L,
                memberId = 3L,
                "책장3",
                deletedAt = LocalDateTime.now(),
            )
        )
        val bookShelfQueryService = BookShelfQueryServiceImpl(fakeBookShelfRepository)
        // when
        val bookShelfs = bookShelfQueryService.findAll()
        // then
        assertThat(bookShelfs.size).isEqualTo(2)

    }
}

