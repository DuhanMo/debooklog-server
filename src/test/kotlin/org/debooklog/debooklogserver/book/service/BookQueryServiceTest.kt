package org.debooklog.debooklogserver.book.service

import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockk
import org.debooklog.debooklogserver.book.domain.BookInformationData
import org.debooklog.debooklogserver.book.domain.BookInformationGetter

class BookQueryServiceTest : BehaviorSpec({
    val bookInformationGetter = mockk<BookInformationGetter>()
    val bookQueryService = BookQueryService(bookInformationGetter)

    Given("카카오 검색 결과가 존재하는 경우") {
        every { bookInformationGetter.search("홍길동전") } returns
            listOf(
                BookInformationData(
                    title = "홍길동전",
                    author = "권혁래",
                    isbn = listOf("8998110571", "9788998110574"),
                    thumbnail = "https://thumbnail.co.kr",
                ),
            )

        When("책을 검색하면") {
            val actual = bookQueryService.search("홍길동전")

            Then("책 정보를 응답한다") {
                actual.first().title shouldBe "홍길동전"
                actual.first().author shouldBe "권혁래"
                actual.first().isbn shouldBe listOf("8998110571", "9788998110574")
                actual.first().thumbnail shouldBe "https://thumbnail.co.kr"
            }
        }
    }
})
