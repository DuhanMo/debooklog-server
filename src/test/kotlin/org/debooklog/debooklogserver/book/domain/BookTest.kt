package org.debooklog.debooklogserver.book.domain

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatCode
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.Test

class BookTest {
    @Test
    fun `BookRegisterCommand를 통해 Book을 생성한다`() {
        // given
        val bookRegisterCommand =
            BookRegisterCommand(
                memberId = 1L,
                title = "title",
                author = "author",
                isbn = listOf("1234567890", "0000000000"),
                thumbnail = "www.thumbnail.com",
            )
        // when
        val actual = Book.from(bookRegisterCommand)
        // then
        assertThat(actual.memberId).isEqualTo(1L)
        assertThat(actual.title).isEqualTo("title")
        assertThat(actual.author).isEqualTo("author")
        assertThat(actual.isbn).isEqualTo(listOf("1234567890", "0000000000"))
        assertThat(actual.thumbnail).isEqualTo("www.thumbnail.com")
    }

    @Test
    fun `isbn이 동일한 책이 이미 저장된 경우 중복검증하면 예외 발생한다`() {
        // given
        val savedBook1 =
            Book(
                id = 1L,
                memberId = 1L,
                title = "title",
                author = "author",
                isbn = listOf("1111111111", "2222222222"),
                thumbnail = "www.thumbnail.com",
            )
        val savedBook2 =
            Book(
                id = 2L,
                memberId = 1L,
                title = "title",
                author = "author",
                isbn = listOf("3333333333", "4444444444"),
                thumbnail = "www.thumbnail.com",
            )
        val newBook =
            Book(
                id = null,
                memberId = 1L,
                title = "title",
                author = "author",
                isbn = listOf("4444444444"),
                thumbnail = "www.thumbnail.com",
            )
        // when & then
        assertThatThrownBy {
            newBook.validateForDuplicate(listOf(savedBook1, savedBook2))
        }
    }

    @Test
    fun `동일한 isbn이 없는 경우 중복검증하면 예외 발생하지 않는다`() {
        // given
        val savedBook1 =
            Book(
                id = 1L,
                memberId = 1L,
                title = "title",
                author = "author",
                isbn = listOf("1111111111", "2222222222"),
                thumbnail = "www.thumbnail.com",
            )
        val savedBook2 =
            Book(
                id = 2L,
                memberId = 1L,
                title = "title",
                author = "author",
                isbn = listOf("3333333333", "4444444444"),
                thumbnail = "www.thumbnail.com",
            )
        val newBook =
            Book(
                id = null,
                memberId = 1L,
                title = "title",
                author = "author",
                isbn = listOf("5555555555"),
                thumbnail = "www.thumbnail.com",
            )
        // when
        assertThatCode {
            newBook.validateForDuplicate(listOf(savedBook1, savedBook2))
        }.doesNotThrowAnyException()
    }
}
