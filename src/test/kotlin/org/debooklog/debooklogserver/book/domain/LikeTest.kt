package org.debooklog.debooklogserver.book.domain

import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe

class LikeTest : BehaviorSpec({
    Given("Like 를 삭제하는 경우") {
        val like = Like(bookId = 1L, memberId = 1L)

        When("Like 를 삭제하면") {
            val deletedLike = like.delete()

            Then("Like 가 소프트 딜리트 된다") {
                deletedLike.deletedAt shouldNotBe null
                deletedLike.isDeleted shouldBe true
            }
        }
    }
})
