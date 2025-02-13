package org.debooklog.core.book.model

import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import java.time.Instant.now

class BookLikeTest : BehaviorSpec({
    Given("Like 를 삭제하는 경우") {
        val bookLike = BookLike(bookId = 1L, memberId = 1L, now = now())

        When("Like 를 삭제하면") {
            val deletedLike = bookLike.delete()

            Then("Like 가 소프트 딜리트 된다") {
                deletedLike.deletedAt shouldNotBe null
                deletedLike.isDeleted shouldBe true
            }
        }
    }
})
