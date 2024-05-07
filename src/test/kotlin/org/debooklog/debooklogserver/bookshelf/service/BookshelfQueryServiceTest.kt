package org.debooklog.debooklogserver.bookshelf.service

import org.assertj.core.api.Assertions.assertThat
import org.debooklog.debooklogserver.bookshelf.domain.Bookshelf
import org.debooklog.debooklogserver.bookshelf.mock.FakeBookshelfRepository
import org.junit.jupiter.api.Test
import java.time.LocalDateTime.now

class BookshelfQueryServiceTest {
    private lateinit var bookshelfQueryService: BookshelfQueryServiceImpl

    @Test
    fun `findAll 은 삭제되지 않은 모든 Bookshelf 를 조회한다`() {
        // given
        val fakeBookshelfRepository = FakeBookshelfRepository()
        fakeBookshelfRepository.save(
            Bookshelf(
                id = 1L,
                memberId = 1L,
                name = "책장1",
                createdAt = now(),
                updatedAt = now(),
                deletedAt = null,
            ),
        )
        fakeBookshelfRepository.save(
            Bookshelf(
                id = 2L,
                memberId = 2L,
                name = "책장2",
                createdAt = now(),
                updatedAt = now(),
                deletedAt = null,
            ),
        )
        fakeBookshelfRepository.save(
            Bookshelf(
                id = 3L,
                memberId = 3L,
                name = "책장3",
                createdAt = now(),
                updatedAt = now(),
                deletedAt = now(),
            ),
        )
        bookshelfQueryService = BookshelfQueryServiceImpl(fakeBookshelfRepository)
        // when
        val bookShelves = bookshelfQueryService.findAll()
        // then
        assertThat(bookShelves.size).isEqualTo(2)
    }
}
