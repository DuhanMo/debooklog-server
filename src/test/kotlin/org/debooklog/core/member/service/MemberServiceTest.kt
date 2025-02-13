package org.debooklog.core.member.service

import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import org.debooklog.core.member.model.Member
import org.debooklog.core.member.model.SocialProvider.GOOGLE
import org.debooklog.mock.FakeMemberRepository
import java.time.Instant.now

class MemberServiceTest : BehaviorSpec({
    lateinit var sut: MemberService

    Given("회원탈퇴 하는 경우") {
        val fakeMemberRepository = FakeMemberRepository()
        val savedMember =
            fakeMemberRepository.save(
                Member(
                    id = 0,
                    name = "홍길동",
                    email = "test@gmail.com",
                    socialId = "123123",
                    provider = GOOGLE,
                    profileImage = "imageUrl.com",
                    createdAt = now(),
                    updatedAt = now(),
                    deletedAt = null,
                    isDeleted = false,
                ),
            )
        sut = MemberService(fakeMemberRepository)

        When("회원 탈퇴하면") {
            sut.withdrawal(savedMember.id)

            Then("소프트 딜리트된다") {
                fakeMemberRepository.getById(savedMember.id).isDeleted shouldBe true
                fakeMemberRepository.getById(savedMember.id).deletedAt shouldNotBe null
            }
        }
    }
})
