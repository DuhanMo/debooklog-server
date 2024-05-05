package org.debooklog.debooklogserver.bookshelf.service

import org.assertj.core.api.Assertions.assertThat
import org.debooklog.debooklogserver.bookshelf.domain.BookShelfNameGenerator
import org.debooklog.debooklogserver.bookshelf.mock.FakeBookShelfRepository
import org.debooklog.debooklogserver.member.domain.Member
import org.debooklog.debooklogserver.member.domain.SocialProvider
import org.debooklog.debooklogserver.member.mock.FakeMemberRepository
import org.junit.jupiter.api.Test
import java.time.LocalDateTime.now

class BookShelfServiceTest {
    private lateinit var bookShelfService: BookShelfServiceImpl

    @Test
    fun `BookShelf 를 저장할 수 있다`() {
        // given
        val fakeBookShelfRepository = FakeBookShelfRepository()
        val bookShelfNameGenerator = BookShelfNameGenerator()
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
        bookShelfService = BookShelfServiceImpl(bookShelfNameGenerator, fakeBookShelfRepository, fakeMemberRepository)
        // when
        bookShelfService.create(memberId = 1L)
        // then
        val savedBookShelf = fakeBookShelfRepository.getById(1L)
        assertThat(savedBookShelf.name).contains("홍길동")
        assertThat(savedBookShelf.memberId).isEqualTo(1L)
    }
}
