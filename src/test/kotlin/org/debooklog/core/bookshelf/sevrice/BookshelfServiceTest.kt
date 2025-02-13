package org.debooklog.core.bookshelf.sevrice

import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import org.debooklog.core.bookshelf.model.Bookshelf
import org.debooklog.core.bookshelf.service.BookshelfService
import org.debooklog.mock.FakeBookshelfRepository
import java.time.Instant.now

class BookshelfServiceTest : BehaviorSpec({
    lateinit var sut: BookshelfService

    Given("책장이 존재하는 경우") {
        val fakeBookshelfRepository = FakeBookshelfRepository()
        fakeBookshelfRepository.save(
            Bookshelf(
                id = 1L,
                memberId = 1L,
                name = "평화로운 길동의 책장",
                imageUrl = "imageUrl.com",
                createdAt = now(),
                updatedAt = now(),
                deletedAt = null,
                isDeleted = false,
            ),
        )
        sut = BookshelfService(fakeBookshelfRepository)

        When("책장이름을 수정하면") {
            sut.update(1L, "수정된 책장이름", 1L)

            Then("책장이름이 정상 수정된다") {
                val bookshelf = fakeBookshelfRepository.getById(1L)
                bookshelf.name shouldBe "수정된 책장이름"
            }
        }
    }
})
