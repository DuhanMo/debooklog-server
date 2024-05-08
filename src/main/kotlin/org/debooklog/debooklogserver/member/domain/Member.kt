package org.debooklog.debooklogserver.member.domain

import java.time.LocalDateTime
import java.time.LocalDateTime.now

class Member(
    val id: Long?,
    val name: String,
    val email: String,
    val password: String,
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
                email = command.email,
                password = command.passwrod,
                socialId = "",
                provider = SocialProvider.GITHUB,
                createdAt = now(),
                updatedAt = now(),
            )
    }
}
