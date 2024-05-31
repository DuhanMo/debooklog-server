package org.debooklog.debooklogserver.book.service

import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import org.debooklog.debooklogserver.book.mock.FakeBookRepository
import org.debooklog.debooklogserver.book.mock.FakeLikeRepository
import org.debooklog.debooklogserver.fixture.createBookFixture

class LikeServiceTest : BehaviorSpec({
    lateinit var sut: LikeServiceImpl

    Given("좋아요를 생성하는 경우") {
        val fakeBookRepository = FakeBookRepository()
        val fakeLikeRepository = FakeLikeRepository()
        fakeBookRepository.save(createBookFixture(id = 1, memberId = 1L, bookshelfId = 1L))

        sut = LikeServiceImpl(fakeBookRepository, fakeLikeRepository)

        When("좋아요를 생성하면") {
            sut.create(1L, 1L)

            Then("새 좋아요가 생성된다") {
                fakeLikeRepository.existsByBookIdAndMemberId(1L, 1L) shouldBe true
            }
            Then("좋아요한 책의 좋아요 수가 증가한다") {
                fakeBookRepository.getById(1L).likeCount shouldBe 1
            }
        }

        When("좋아요 생성을 중복요청하면") {
            sut.create(1L, 1L)
            sut.create(1L, 1L)

            Then("좋아요한 책의 좋아요 수가 중복으로 증가하지 않는다") {
                fakeBookRepository.getById(1L).likeCount shouldBe 1
            }
        }
    }
})
