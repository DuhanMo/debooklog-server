package org.debooklog.debooklogserver.book.service

import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import org.assertj.core.api.Assertions.assertThat
import org.debooklog.debooklogserver.book.domain.Book
import org.debooklog.debooklogserver.book.domain.BookRegisterCommand
import org.debooklog.debooklogserver.book.mock.FakeBookRepository
import java.time.LocalDateTime.now

class BookServiceTest : BehaviorSpec({
    lateinit var sut: BookServiceImpl

    Given("책을 저장하는 경우") {
        val fakeBookRepository = FakeBookRepository()
        sut = BookServiceImpl(fakeBookRepository)

        When("책을 저장하면") {
            sut.register(
                BookRegisterCommand(
                    memberId = 1L,
                    bookshelfId = 1L,
                    title = "title",
                    author = "author",
                    isbn = listOf("111111111"),
                    thumbnail = "thumbnail",
                ),
            )

            Then("책이 저장된다") {
                val books = fakeBookRepository.findAllByMemberId(1L)
                assertThat(books.size).isEqualTo(1)
                assertThat(books.first().title).isEqualTo("title")
            }
        }
    }

    Given("책을 삭제하는 경우") {
        val fakeBookRepository = FakeBookRepository()
        fakeBookRepository.save(
            Book(
                id = 1L,
                memberId = 1L,
                bookshelfId = 1L,
                title = "노인과바다",
                author = "어니스트 해밍웨이",
                isbn = listOf("1231231230"),
                thumbnail = "thumbnail",
                createdAt = now(),
                updatedAt = now(),
                deletedAt = null,
                isDeleted = false,
            ),
        )
        sut = BookServiceImpl(fakeBookRepository)

        When("책을 삭제하면") {
            sut.delete(1L)

            Then("소프트 딜리트 된다") {
                val deletedBook = fakeBookRepository.getById(1L)
                deletedBook.isDeleted shouldBe true
                deletedBook.deletedAt shouldNotBe null
            }
        }
    }
})
