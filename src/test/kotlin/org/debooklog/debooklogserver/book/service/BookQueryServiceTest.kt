package org.debooklog.debooklogserver.book.service

import org.assertj.core.api.Assertions.assertThat
import org.debooklog.debooklogserver.book.domain.BookInformationData
import org.debooklog.debooklogserver.book.mock.FakeBookInformationGetter
import org.junit.jupiter.api.Test

class BookQueryServiceTest {
    private lateinit var bookQueryService: BookQueryServiceImpl

    @Test
    fun `책 제목을 검색하는 경우 책이 존재하면 책을 응답한다`() {
        // given
        val books =
            listOf(
                BookInformationData(
                    title = "미움받을용기",
                    author = "author1",
                    isbn = listOf("111111111"),
                    thumbnail = "thumbnail",
                ),
            )
        val fakeBookInformationGetter = FakeBookInformationGetter(stub = books)
        bookQueryService = BookQueryServiceImpl(fakeBookInformationGetter)
        // when
        val actual = bookQueryService.search("미움받을용기")
        // then
        assertThat(actual.size).isEqualTo(1)
        assertThat(actual.first().title).isEqualTo("미움받을용기")
        assertThat(actual.first().author).isEqualTo("author1")
        assertThat(actual.first().isbn).contains("111111111")
        assertThat(actual.first().thumbnail).isEqualTo("thumbnail")
    }

    @Test
    fun `책 제목을 검색하는 경우 책이 존재하지 않으면 빈 리스트를 응답한다`() {
        // given
        val fakeBookInformationGetter = FakeBookInformationGetter(stub = emptyList())
        bookQueryService = BookQueryServiceImpl(fakeBookInformationGetter)
        // when
        val actual = bookQueryService.search("검색결과없는타이틀")
        // then
        assertThat(actual).isEmpty()
    }
}
