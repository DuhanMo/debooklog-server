package org.debooklog.debooklogserver.bookshelf.domain

import org.assertj.core.api.Assertions.assertThat
import org.debooklog.debooklogserver.book.mock.FakeBookShelfNameGenerator
import org.junit.jupiter.api.Test

class BookShelfTest {
    @Test
    fun `BookShelf가 생성되는 경우 책장이름은 랜덤으로 생성된다`() {
        // given
        val fakeBookShelfNameGenerator = FakeBookShelfNameGenerator("책장이름")
        // when
        val bookShelf =
            BookShelf(
                memberId = 1L,
                bookShelfNameGenerator = fakeBookShelfNameGenerator,
            )
        // then
        assertThat(bookShelf.name).isEqualTo("책장이름")
    }

    @Test
    fun `BookShelf의 이름을 변경할 수 있다`() {
        // given
        val fakeBookShelfNameGenerator = FakeBookShelfNameGenerator("책장이름")
        val bookShelf =
            BookShelf(
                memberId = 1L,
                bookShelfNameGenerator = fakeBookShelfNameGenerator,
            )
        // when
        val updatedBookShelf = bookShelf.update("수정된 책장이름")
        // then
        assertThat(updatedBookShelf.name).isEqualTo("수정된 책장이름")
    }
}
