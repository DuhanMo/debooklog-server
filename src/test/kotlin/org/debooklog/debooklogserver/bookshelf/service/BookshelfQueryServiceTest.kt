package org.debooklog.debooklogserver.bookshelf.service

import org.assertj.core.api.Assertions.assertThat
import org.debooklog.debooklogserver.book.mock.FakeBookRepository
import org.debooklog.debooklogserver.bookshelf.mock.FakeBookshelfRepository
import org.debooklog.debooklogserver.core.bookshelf.model.Bookshelf
import org.debooklog.debooklogserver.fixture.createBookFixture
import org.junit.jupiter.api.Test
import java.time.LocalDateTime.now

class BookshelfQueryServiceTest {
    private lateinit var bookshelfQueryService: BookshelfQueryService

    @Test
    fun `findAll 은 삭제되지 않은 모든 Bookshelf 를 조회한다`() {
        // given
        val fakeBookshelfRepository = FakeBookshelfRepository()
        val fakeBookRepository = FakeBookRepository()
        fakeBookshelfRepository.save(
            Bookshelf(
                id = 1L,
                memberId = 1L,
                name = "책장1",
                imageUrl = "imageUrl.com",
                createdAt = now(),
                updatedAt = now(),
                deletedAt = null,
                isDeleted = false,
            ),
        )
        fakeBookshelfRepository.save(
            Bookshelf(
                id = 2L,
                memberId = 2L,
                name = "책장2",
                imageUrl = "imageUrl.com",
                createdAt = now(),
                updatedAt = now(),
                deletedAt = null,
                isDeleted = false,
            ),
        )
        fakeBookshelfRepository.save(
            Bookshelf(
                id = 3L,
                memberId = 3L,
                name = "책장3",
                imageUrl = "imageUrl.com",
                createdAt = now(),
                updatedAt = now(),
                deletedAt = now(),
                isDeleted = false,
            ),
        )
        bookshelfQueryService = BookshelfQueryService(fakeBookshelfRepository, fakeBookRepository)
        // when
        val bookShelves = bookshelfQueryService.findAll()
        // then
        assertThat(bookShelves.size).isEqualTo(2)
    }

    @Test
    fun `find 는 책장과 책장에 포함된 책을 조회한다`() {
        // given
        val fakeBookshelfRepository = FakeBookshelfRepository()
        val fakeBookRepository = FakeBookRepository()
        fakeBookshelfRepository.save(
            Bookshelf(
                id = 1L,
                memberId = 1L,
                name = "책장1",
                imageUrl = "imageUrl.com",
                createdAt = now(),
                updatedAt = now(),
                deletedAt = null,
                isDeleted = false,
            ),
        )
        fakeBookRepository.save(createBookFixture(id = 1, memberId = 1L, bookshelfId = 1L, title = "책1"))
        fakeBookRepository.save(createBookFixture(id = 2, memberId = 1L, bookshelfId = 1L, title = "책2"))
        fakeBookRepository.save(createBookFixture(id = 3, memberId = 1L, bookshelfId = 1L, title = "책3"))

        bookshelfQueryService = BookshelfQueryService(fakeBookshelfRepository, fakeBookRepository)
        // when
        val bookshelf = bookshelfQueryService.find(1L)
        // then
        assertThat(bookshelf.books.size).isEqualTo(3)
    }
}
