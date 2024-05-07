package org.debooklog.debooklogserver.bookshelf.service

import org.assertj.core.api.Assertions.assertThat
import org.debooklog.debooklogserver.bookshelf.domain.BookshelfNameGenerator
import org.debooklog.debooklogserver.bookshelf.mock.FakeBookshelfRepository
import org.debooklog.debooklogserver.member.domain.Member
import org.debooklog.debooklogserver.member.domain.SocialProvider
import org.debooklog.debooklogserver.member.mock.FakeMemberRepository
import org.junit.jupiter.api.Test
import java.time.LocalDateTime.now

class BookshelfServiceTest {
    private lateinit var bookshelfService: BookshelfServiceImpl

    @Test
    fun `Bookshelf 를 저장할 수 있다`() {
        // given
        val fakeBookshelfRepository = FakeBookshelfRepository()
        val bookshelfNameGenerator = BookshelfNameGenerator()
        val fakeMemberRepository = FakeMemberRepository()
        fakeMemberRepository.save(
            Member(
                id = 1L,
                name = "홍길동",
                socialId = "123456",
                provider = SocialProvider.GITHUB,
                createdAt = now(),
                updatedAt = now(),
            ),
        )
        bookshelfService = BookshelfServiceImpl(bookshelfNameGenerator, fakeBookshelfRepository, fakeMemberRepository)
        // when
        bookshelfService.create(memberId = 1L)
        // then
        val savedBookshelf = fakeBookshelfRepository.getById(1L)
        assertThat(savedBookshelf.name).contains("홍길동")
        assertThat(savedBookshelf.memberId).isEqualTo(1L)
    }
}
