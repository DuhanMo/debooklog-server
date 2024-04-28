package org.debooklog.debooklogserver.book.domain

import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe

class BookTest: BehaviorSpec({
    Given("BookRegisterCommand 를 통해") {
        val bookRegisterCommand = BookRegisterCommand(
            memberId = 1L,
            title = "title",
            author = "author",
            isbn = listOf("1234567890", "0000000000"),
            thumbnail = "www.thumbnail.com"
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
})
