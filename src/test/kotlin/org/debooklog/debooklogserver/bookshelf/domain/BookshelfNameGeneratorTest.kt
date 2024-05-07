package org.debooklog.debooklogserver.bookshelf.domain

import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.string.shouldContain

class BookshelfNameGeneratorTest : BehaviorSpec({
    Given("BookshelfNameGenerator 의 generate를 호출하는 경우") {
        val bookshelfNameGenerator = BookshelfNameGenerator()
        When("파라미터로 멤버이름을 전달하면") {
            val actual = bookshelfNameGenerator.generate(memberName = "홍길동")
            Then("생성된 랜덤이름은 멤버이름을 포함한다") {
                actual shouldContain "홍길동"
            }
        }
    }
})
