package org.debooklog.debooklogserver.bookshelf.domain

import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.string.shouldContain

class BookShelfNameGeneratorTest : BehaviorSpec({
    Given("BookShelfNameGenerator 의 generate를 호출하는 경우") {
        val bookShelfNameGenerator = BookShelfNameGenerator()
        When("파라미터로 멤버이름을 전달하면") {
            val actual = bookShelfNameGenerator.generate(memberName = "홍길동")
            Then("생성된 랜덤이름은 멤버이름을 포함한다") {
                actual shouldContain "홍길동"
            }
        }
    }
})
