package org.debooklog.debooklogserver.member.service.port

import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import org.debooklog.debooklogserver.member.domain.Member
import org.debooklog.debooklogserver.member.domain.SocialProvider.GOOGLE
import org.debooklog.debooklogserver.member.mock.FakeMemberRepository
import java.time.LocalDateTime.now

class MemberServiceTest : BehaviorSpec({
    lateinit var sut: MemberServiceImpl

    Given("회원탈퇴 하는 경우") {
        val fakeMemberRepository = FakeMemberRepository()
        val savedMember =
            fakeMemberRepository.save(
                Member(
                    id = null,
                    name = "홍길동",
                    email = "test@gmail.com",
                    socialId = "123123",
                    provider = GOOGLE,
                    createdAt = now(),
                    updatedAt = now(),
                    deletedAt = null,
                    isDeleted = false,
                ),
            )
        sut = MemberServiceImpl(fakeMemberRepository)

        When("회원 탈퇴하면") {
            val withdrawalMember = sut.withdrawal(savedMember.id!!)

            Then("소프트 딜리트된다") {
                withdrawalMember.isDeleted shouldBe true
                withdrawalMember.deletedAt shouldNotBe null
            }
        }
    }
})
