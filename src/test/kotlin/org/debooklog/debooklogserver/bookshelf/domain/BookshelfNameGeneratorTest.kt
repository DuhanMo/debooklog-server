package org.debooklog.debooklogserver.bookshelf.domain

import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.string.shouldContain
import org.debooklog.debooklogserver.core.bookshelf.model.BookshelfNameGenerator

class BookshelfNameGeneratorTest : BehaviorSpec({
    Given("BookshelfNameGenerator 의 generate를 호출하는 경우") {
        When("파라미터로 멤버이름을 전달하면") {
            val actual = BookshelfNameGenerator.generate(memberName = "홍길동")

            Then("생성된 랜덤이름은 멤버이름을 포함한다") {
                actual shouldContain "홍길동"
            }
        }
    }
})
