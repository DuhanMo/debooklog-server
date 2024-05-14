package org.debooklog.debooklogserver.bookshelf.infrastructure.persistence

import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.string.shouldContain
import org.debooklog.debooklogserver.member.domain.MemberCreateCommand
import org.debooklog.debooklogserver.member.service.MemberServiceImpl
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles

@SpringBootTest
@ActiveProfiles("test")
class BookshelfListenerTest(
    @Autowired private val memberService: MemberServiceImpl,
    @Autowired private val bookshelfRepository: BookshelfRepositoryImpl,
) : BehaviorSpec({
        Given("MemberService를 통해 Member를 생성하는 경우") {
            val command = MemberCreateCommand("홍길동", "test@gmail.com", "password")
            When("Member를 생성하면") {
                memberService.create(command)
                Then("Bookshelf가 생성된다") {
                    bookshelfRepository.findAll().first().name shouldContain "홍길동"
                }
            }
        }
    })
