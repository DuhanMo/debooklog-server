package org.debooklog.core.bookshelf.model

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.date.shouldBeAfter
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import java.time.Instant.now

class BookshelfTest : BehaviorSpec({
    Given("Bookshelf를 생성하는 경우") {
        When("생성자 파라미터를 전달하면") {
            val bookshelf =
                Bookshelf(
                    id = 0,
                    memberId = 1L,
                    name = "책장",
                    imageUrl = "imageUrl.com",
                    createdAt = now(),
                    updatedAt = now(),
                    deletedAt = null,
                    isDeleted = false,
                )

            Then("파라미터 값으로 생성된다") {
                bookshelf.name shouldBe "책장"
                bookshelf.memberId shouldBe 1L
            }
        }

        When("createdAt과 updatedAt을 설정하지 않으면") {
            val bookshelf =
                Bookshelf(
                    memberId = 1L,
                    imageUrl = "imageUrl.com",
                    name = "책장",
                    now = now(),
                )

            Then("자동으로 초기화 된다") {
                bookshelf.createdAt shouldNotBe null
                bookshelf.updatedAt shouldNotBe null
            }
        }
    }

    Given("Bookshelf를 수정하는 경우") {
        val now = now()
        val bookshelf =
            Bookshelf(
                id = 0,
                memberId = 1L,
                name = "책장",
                imageUrl = "imageUrl.com",
                createdAt = now,
                updatedAt = now,
                deletedAt = null,
                isDeleted = false,
            )
        When("이름을 전달하면") {
            val updatedBookshelf = bookshelf.update("수정책장", 1L)

            Then("전달한 이름으로 수정된다") {
                updatedBookshelf.name shouldBe "수정책장"
            }
        }

        When("수정요청하면") {
            val updatedBookshelf = bookshelf.update("수정책장", 1L)

            Then("updatedAt이 갱신된다") {
                updatedBookshelf.updatedAt shouldBeAfter updatedBookshelf.createdAt
            }
        }
    }

    Given("Bookshelf 의 memberId와 요청자의 memberId가 다른 경우") {
        val now = now()
        val bookshelf =
            Bookshelf(
                id = 0,
                memberId = 1L,
                name = "책장",
                imageUrl = "imageUrl.com",
                createdAt = now,
                updatedAt = now,
                deletedAt = null,
                isDeleted = false,
            )

        When("Bookshelf 이름을 수정하는 경우") {
            Then("예외 발생한다") {
                shouldThrow<IllegalArgumentException> {
                    bookshelf.update("수정책장", 99L)
                }
            }
        }
    }
})
