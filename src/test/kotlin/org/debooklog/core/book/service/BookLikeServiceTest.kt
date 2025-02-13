package org.debooklog.core.book.service

import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import org.debooklog.core.book.model.BookLike
import org.debooklog.mock.FakeBookLikeRepository
import org.debooklog.mock.FakeBookRepository
import java.time.Instant.now

class BookLikeServiceTest : BehaviorSpec({
    lateinit var sut: BookLikeService

    Given("좋아요를 생성하는 경우") {
        val fakeBookRepository = FakeBookRepository()
        val fakeLikeRepository = FakeBookLikeRepository()
        fakeBookRepository.save(org.debooklog.fixture.createBookFixture(id = 1, memberId = 1L, bookshelfId = 1L))

        sut = BookLikeService(fakeBookRepository, fakeLikeRepository)

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

    Given("좋아요가 있는 경우") {
        val fakeBookRepository = FakeBookRepository()
        val fakeLikeRepository = FakeBookLikeRepository()
        fakeBookRepository.save(
            org.debooklog.fixture.createBookFixture(
                id = 1,
                memberId = 1L,
                bookshelfId = 1L,
                likeCount = 100,
            ),
        )
        fakeLikeRepository.save(BookLike(1L, 1L, now()))

        sut = BookLikeService(fakeBookRepository, fakeLikeRepository)

        When("좋아요를 제거하면") {
            sut.remove(1L, 1L)

            Then("책의 좋아요 수가 감소한다") {
                fakeBookRepository.getById(1L).likeCount shouldBe 99
            }

            Then("좋아요가 소프트 딜리트 된다") {
                fakeLikeRepository.findByBookIdAndMemberId(1L, 1L)?.deletedAt shouldNotBe null
                fakeLikeRepository.findByBookIdAndMemberId(1L, 1L)?.isDeleted shouldBe true
            }
        }
    }
    Given("좋아요가 없는 경우") {
        val fakeBookRepository = FakeBookRepository()
        val fakeLikeRepository = FakeBookLikeRepository()
        fakeBookRepository.save(
            org.debooklog.fixture.createBookFixture(
                id = 1,
                memberId = 1L,
                bookshelfId = 1L,
                likeCount = 100,
            ),
        )

        sut = BookLikeService(fakeBookRepository, fakeLikeRepository)

        When("좋아요를 제거하면") {
            sut.remove(1L, 1L)

            Then("책의 좋아요 수가 감소하지 않는다") {
                fakeBookRepository.getById(1L).likeCount shouldBe 100
            }
        }
    }
})
