package org.debooklog.debooklogserver.bookshelf.domain

import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.date.shouldBeAfter
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import java.time.LocalDateTime.now

class BookShelfTest : BehaviorSpec({
    Given("BookShelf를 생성하는 경우") {
        When("생성자 파라미터를 전달하면") {
            val bookShelf =
                BookShelf(
                    id = null,
                    memberId = 1L,
                    name = "책장",
                    createdAt = now(),
                    updatedAt = now(),
                    deletedAt = null,
                )

            Then("파라미터 값으로 생성된다") {
                bookShelf.name shouldBe "책장"
                bookShelf.memberId shouldBe 1L
            }
        }

        When("createdAt과 updatedAt을 설정하지 않으면") {
            val bookShelf =
                BookShelf(
                    memberId = 1L,
                    name = "책장",
                    createdAt = now(),
                )

            Then("자동으로 초기화 된다") {
                bookShelf.createdAt shouldNotBe null
                bookShelf.updatedAt shouldNotBe null
            }
        }
    }
    Given("BookShelf를 수정하는 경우") {
        val now = now()
        val bookShelf =
            BookShelf(
                id = null,
                memberId = 1L,
                name = "책장",
                createdAt = now,
                updatedAt = now,
                deletedAt = null,
            )
        When("이름을 전달하면") {
            val updatedBookShelf = bookShelf.update("수정책장")

            Then("전달한 이름으로 수정된다") {
                updatedBookShelf.name shouldBe "수정책장"
            }
        }

        When("수정요청하면") {
            val updatedBookShelf = bookShelf.update("수정책장")

            Then("updatedAt이 갱신된다") {
                updatedBookShelf.updatedAt shouldBeAfter updatedBookShelf.createdAt
            }
        }
    }
})
