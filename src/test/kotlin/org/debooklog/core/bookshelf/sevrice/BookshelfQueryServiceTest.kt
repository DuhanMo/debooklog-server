package org.debooklog.core.bookshelf.sevrice

import org.assertj.core.api.Assertions.assertThat
import org.debooklog.core.bookshelf.model.Bookshelf
import org.debooklog.core.bookshelf.service.BookshelfQueryService
import org.debooklog.core.member.model.Member
import org.debooklog.core.member.model.SocialProvider.GOOGLE
import org.debooklog.mock.FakeBookLikeRepository
import org.debooklog.mock.FakeBookRepository
import org.debooklog.mock.FakeBookshelfRepository
import org.debooklog.mock.FakeMemberRepository
import org.junit.jupiter.api.Test
import java.time.Instant.now

class BookshelfQueryServiceTest {
    private lateinit var bookshelfQueryService: BookshelfQueryService

    @Test
    fun `findAll 은 삭제되지 않은 모든 Bookshelf 를 조회한다`() {
        // given
        val fakeMemberRepository = FakeMemberRepository()
        val fakeBookshelfRepository = FakeBookshelfRepository()
        val fakeBookRepository = FakeBookRepository()
        val fakeBookLikeRepository = FakeBookLikeRepository()
        fakeMemberRepository.save(
            Member(
                id = 1,
                name = "홍길동",
                email = "test@test.com",
                socialId = "socialId",
                provider = GOOGLE,
                profileImage = "image.url",
                createdAt = now(),
                updatedAt = now(),
                deletedAt = null,
                isDeleted = false,
            ),
        )
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
                isDeleted = true,
            ),
        )
        bookshelfQueryService =
            BookshelfQueryService(
                fakeMemberRepository,
                fakeBookshelfRepository,
                fakeBookRepository,
                fakeBookLikeRepository,
            )
        // when
        val bookShelves = bookshelfQueryService.findAll()
        // then
        assertThat(bookShelves.size).isEqualTo(2)
    }

    @Test
    fun `find 는 책장과 책장에 포함된 책을 조회한다`() {
        // given
        val fakeMemberRepository = FakeMemberRepository()
        val fakeBookshelfRepository = FakeBookshelfRepository()
        val fakeBookRepository = FakeBookRepository()
        val fakeBookLikeRepository = FakeBookLikeRepository()
        fakeMemberRepository.save(
            Member(
                id = 1,
                name = "홍길동",
                email = "test@test.com",
                socialId = "socialId",
                provider = GOOGLE,
                profileImage = "image.url",
                createdAt = now(),
                updatedAt = now(),
                deletedAt = null,
                isDeleted = false,
            ),
        )
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
        fakeBookRepository.save(
            org.debooklog.fixture.createBookFixture(
                id = 1,
                memberId = 1L,
                bookshelfId = 1L,
                title = "책1",
            ),
        )
        fakeBookRepository.save(
            org.debooklog.fixture.createBookFixture(
                id = 2,
                memberId = 1L,
                bookshelfId = 1L,
                title = "책2",
            ),
        )
        fakeBookRepository.save(
            org.debooklog.fixture.createBookFixture(
                id = 3,
                memberId = 1L,
                bookshelfId = 1L,
                title = "책3",
            ),
        )

        bookshelfQueryService =
            BookshelfQueryService(
                fakeMemberRepository,
                fakeBookshelfRepository,
                fakeBookRepository,
                fakeBookLikeRepository,
            )
        // when
        val bookshelf = bookshelfQueryService.find(1L)
        // then
        assertThat(bookshelf.books.size).isEqualTo(3)
    }
}
