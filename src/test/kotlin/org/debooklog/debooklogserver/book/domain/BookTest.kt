package org.debooklog.debooklogserver.book.domain

import io.kotest.assertions.throwables.shouldNotThrow
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe

class BookTest : BehaviorSpec({
    Given("Book 을 생성하는 경우") {
        val bookRegisterCommand =
            BookRegisterCommand(
                memberId = 1L,
                bookshelfId = 1L,
                title = "title",
                author = "author",
                isbn = listOf("1234567890", "0000000000"),
                thumbnail = "www.thumbnail.com",
            )

        When("Book 을 생성하면") {
            val actual = Book.from(bookRegisterCommand)

            Then("Book 이 생성된다") {
                actual.memberId shouldBe 1L
                actual.title shouldBe "title"
                actual.author shouldBe "author"
                actual.isbn shouldBe listOf("1234567890", "0000000000")
                actual.thumbnail shouldBe "www.thumbnail.com"
            }
        }
    }

    Given("isbn이 동일한 책이 저장되어 있는 경우") {
        val savedBook1 =
            Book(
                id = 1L,
                memberId = 1L,
                bookshelfId = 1L,
                title = "title",
                author = "author",
                isbn = listOf("1111111111", "2222222222"),
                thumbnail = "www.thumbnail.com",
                deletedAt = null,
                isDeleted = false,
            )
        val savedBook2 =
            Book(
                id = 2L,
                memberId = 1L,
                bookshelfId = 1L,
                title = "title",
                author = "author",
                isbn = listOf("3333333333", "4444444444"),
                thumbnail = "www.thumbnail.com",
                deletedAt = null,
                isDeleted = false,
            )
        val newBook =
            Book(
                id = null,
                memberId = 1L,
                bookshelfId = 1L,
                title = "title",
                author = "author",
                isbn = listOf("4444444444"),
                thumbnail = "www.thumbnail.com",
                deletedAt = null,
                isDeleted = false,
            )

        When("Book 을 저장하면") {
            Then("예외 발생한다") {
                shouldThrow<DuplicateBookException> {
                    newBook.validateForDuplicate(listOf(savedBook1, savedBook2))
                }
            }
        }
    }

    Given("isbn이 동일한 책이 저장되어 있지 않은 경우") {
        val savedBook1 =
            Book(
                id = 1L,
                memberId = 1L,
                bookshelfId = 1L,
                title = "title",
                author = "author",
                isbn = listOf("1111111111", "2222222222"),
                thumbnail = "www.thumbnail.com",
                deletedAt = null,
                isDeleted = false,
            )
        val savedBook2 =
            Book(
                id = 2L,
                memberId = 1L,
                bookshelfId = 1L,
                title = "title",
                author = "author",
                isbn = listOf("3333333333", "4444444444"),
                thumbnail = "www.thumbnail.com",
                deletedAt = null,
                isDeleted = false,
            )
        val newBook =
            Book(
                id = null,
                memberId = 1L,
                bookshelfId = 1L,
                title = "title",
                author = "author",
                isbn = listOf("5555555555"),
                thumbnail = "www.thumbnail.com",
                deletedAt = null,
                isDeleted = false,
            )

        When("Book 을 저장하면") {
            Then("예외 발생하지 않는다") {
                shouldNotThrow<DuplicateBookException> {
                    newBook.validateForDuplicate(listOf(savedBook1, savedBook2))
                }
            }
        }
    }

    Given("Book 의 memberId와 요청한 memberId가 동일한 경우") {
        val book =
            Book(
                id = null,
                memberId = 1L,
                bookshelfId = 1L,
                title = "title",
                author = "author",
                isbn = listOf("5555555555"),
                thumbnail = "www.thumbnail.com",
                deletedAt = null,
                isDeleted = false,
            )

        When("Book 을 삭제하면") {
            val deletedBook = book.delete(1L)

            Then("Book 이 소프트 딜리트 된다") {
                deletedBook.deletedAt shouldNotBe null
                deletedBook.isDeleted shouldBe true
            }
        }
    }

    Given("Book 의 memberId와 요청한 memberId가 동일하지 않은 경우") {
        val book =
            Book(
                id = null,
                memberId = 1L,
                bookshelfId = 1L,
                title = "title",
                author = "author",
                isbn = listOf("5555555555"),
                thumbnail = "www.thumbnail.com",
                deletedAt = null,
                isDeleted = false,
            )

        When("Book 을 삭제하면") {
            Then("예외 발생한다") {
                shouldThrow<IllegalArgumentException> {
                    book.delete(2L)
                }
            }
        }
    }
})
