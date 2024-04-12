package org.debooklog.debooklogserver.global.data

import org.debooklog.debooklogserver.global.security.JwtProvider
import org.debooklog.debooklogserver.member.domain.Member
import org.debooklog.debooklogserver.member.domain.MemberRepository
import org.debooklog.debooklogserver.member.domain.SocialProvider.GITHUB
import org.slf4j.LoggerFactory
import org.springframework.boot.context.event.ApplicationReadyEvent
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Component

@Component
class ApplicationInitEvent(
    private val memberRepository: MemberRepository,
    private val jwtProvider: JwtProvider,
) {
    private val logger = LoggerFactory.getLogger(javaClass)

    @EventListener(ApplicationReadyEvent::class)
    fun registerMember() {
        val member =
            memberRepository.save(
                Member(
                    name = "홍길동",
                    socialId = "123123123",
                    provider = GITHUB,
                ),
            )
        logger.info(jwtProvider.generateAccessJwt(member))
    }
}
