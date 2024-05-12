package org.debooklog.debooklogserver.member.domain

import org.debooklog.debooklogserver.member.domain.SocialProvider.GITHUB
import java.time.LocalDateTime
import java.time.LocalDateTime.now

class Member(
    val id: Long?,
    val name: String,
    val socialId: String,
    val provider: SocialProvider,
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime,
) {
    companion object {
        fun from(command: MemberCreateCommand): Member =
            Member(
                id = null,
                name = command.name,
                socialId = "",
                provider = GITHUB,
                createdAt = now(),
                updatedAt = now(),
            )
    }
}
