package org.debooklog.debooklogserver.member.domain

import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe

class MemberTest : BehaviorSpec({
    Given("멤버 생성하는 경우") {
        When("command 객체를 통해 멤버를 생성하면") {
            val command = MemberCreateCommand("홍길동", "test@gmail.com", "password")
            val actual = Member.from(command)
            Then("멤버가 생성된다") {
                actual.name shouldBe "홍길동"
                actual.email shouldBe "test@gmail.com"
                actual.password shouldBe "password"
            }
        }
    }
})
