package org.debooklog.core.book.service

import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.collections.shouldContainExactly
import io.kotest.matchers.shouldBe
import org.assertj.core.api.Assertions
import org.debooklog.core.book.model.Book
import org.debooklog.core.book.model.BookInformationData
import org.debooklog.core.book.model.BookRank
import org.debooklog.fixture.createBookFixture
import org.debooklog.mock.FakeBookInformationGetter
import org.debooklog.mock.FakeBookRepository

class BookQueryServiceTest : BehaviorSpec({
    lateinit var sut: BookQueryService

    Given("외부 API 책 검색결과가 존재하는 경우") {
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
        val fakeBookRepository = FakeBookRepository()
        sut = BookQueryService(fakeBookInformationGetter, fakeBookRepository)

        When("책을 검색하면") {
            val actual = sut.search("미움받을용기")

            Then("책을 응답한다") {
                actual.size shouldBe 1
                actual.first().title shouldBe "미움받을용기"
                actual.first().author shouldBe "author1"
                actual.first().isbn shouldBe listOf("111111111")
                actual.first().thumbnail shouldBe "thumbnail"
            }
        }
    }

    Given("외부 API 책 검색결과가 존재하지 않는 경우") {
        val fakeBookInformationGetter = FakeBookInformationGetter(stub = emptyList())
        val fakeBookRepository = FakeBookRepository()
        sut = BookQueryService(fakeBookInformationGetter, fakeBookRepository)

        When("책을 검색하면") {
            val actual = sut.search("검색결과없는타이틀")

            Then("빈 목록을 응답한다") {
                Assertions.assertThat(actual).isEmpty()
            }
        }
    }

    Given("책이 존재하는 경우") {
        val fakeBookInformationGetter = FakeBookInformationGetter()
        val fakeBookRepository = FakeBookRepository()
        createBooks().forEach { book ->
            fakeBookRepository.save(book)
        }
        sut = BookQueryService(fakeBookInformationGetter, fakeBookRepository)

        When("책 랭크를 검색하면") {
            val actual = sut.findBookRanks()

            Then("isbn이 많은 순서대로 정렬하여 응답한다") {
                actual.size shouldBe 3
                actual shouldContainExactly
                    listOf(
                        BookRank(1, "12345678", "책1", 3),
                        BookRank(2, "22345678", "책2", 2),
                        BookRank(3, "32345678", "책3", 1),
                    )
            }
        }
    }
})

private fun createBooks(): List<Book> =
    listOf(
        org.debooklog.fixture.createBookFixture(
            memberId = 1L,
            bookshelfId = 1L,
            title = "책1",
            isbn = listOf("12345678", "1234567890"),
        ),
        org.debooklog.fixture.createBookFixture(
            memberId = 1L,
            bookshelfId = 1L,
            title = "책2",
            isbn = listOf("22345678", "2234567890"),
        ),
        org.debooklog.fixture.createBookFixture(
            memberId = 1L,
            bookshelfId = 1L,
            title = "책3",
            isbn = listOf("32345678", "3234567890"),
        ),
        org.debooklog.fixture.createBookFixture(
            memberId = 2L,
            bookshelfId = 2L,
            title = "책1",
            isbn = listOf("12345678", "1234567890"),
        ),
        org.debooklog.fixture.createBookFixture(
            memberId = 2L,
            bookshelfId = 2L,
            title = "책2",
            isbn = listOf("22345678", "2234567890"),
        ),
        org.debooklog.fixture.createBookFixture(
            memberId = 3L,
            bookshelfId = 3L,
            title = "책1",
            isbn = listOf("12345678", "1234567890"),
        ),
    )
