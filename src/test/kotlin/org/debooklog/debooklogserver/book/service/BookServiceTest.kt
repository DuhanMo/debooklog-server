package org.debooklog.debooklogserver.book.service

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.BehaviorSpec
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.debooklog.debooklogserver.book.domain.Book
import org.debooklog.debooklogserver.book.domain.BookRepository
import org.debooklog.debooklogserver.book.service.dto.BookRegisterCommand

class BookServiceTest : BehaviorSpec({
    val bookRepository = mockk<BookRepository>()
    val bookService = BookService(bookRepository)

    Given("저장한 이력이 없는 책인 경우") {
        every { bookRepository.save(any()) } returnsArgument 0
        every { bookRepository.findAllByMemberId(any()) } returns
            listOf(
                Book(
                    memberId = 1L,
                    title = "저장한책",
                    author = "작가",
                    isbn = listOf("1234"),
                    thumbnail = "썸네일.jpg",
                ),
            )
        When("책을 저장하면") {
            bookService.register(
                BookRegisterCommand(
                    memberId = 1L,
                    title = "리팩터링 3판",
                    author = "마틴파울러",
                    isbn = listOf("8998110571", "9788998110574"),
                    thumbnail = "https://contents.kyobobook.co.kr/sih/fit-in/458x0/pdt/9791162242742.jpg",
                ),
            )
            Then("책이 저장된다") {
                verify(exactly = 1) { bookRepository.save(any()) }
            }
        }
    }

    Given("이미 저장한 책인 경우") {
        val isbn = listOf("1234", "5678")
        every { bookRepository.findAllByMemberId(any()) } returns
            listOf(
                Book(
                    memberId = 1L,
                    title = "저장한책",
                    author = "작가",
                    isbn = isbn,
                    thumbnail = "썸네일.jpg",
                ),
            )

        When("책을 저장하면") {
            Then("예외가 발생한다") {
                shouldThrow<IllegalArgumentException> {
                    bookService.register(
                        BookRegisterCommand(
                            memberId = 1L,
                            title = "새롭게저장하려는책",
                            author = "새작가",
                            isbn = isbn,
                            thumbnail = "썸네일.jpg",
                        ),
                    )
                }
            }
        }
    }
})
