package org.debooklog.debooklogserver.member.service

import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import org.debooklog.debooklogserver.member.domain.MemberCreateCommand
import org.debooklog.debooklogserver.member.mock.FakeMemberRepository

class MemberServiceTest : BehaviorSpec({
    Given("멤버를 생성하는 경우") {
        val fakeMemberRepository = FakeMemberRepository()
        val memberService = MemberServiceImpl(fakeMemberRepository)

        When("멤버 생성을 시도하면") {
            memberService.create(MemberCreateCommand("홍길동", "test@gmail.com", "password"))

            Then("멤버가 생성된다") {
                val member = fakeMemberRepository.getById(1)
                member.name shouldBe "홍길동"
                member.email shouldBe "test@gmail.com"
                member.password shouldBe "password"
            }
        }
    }
})
