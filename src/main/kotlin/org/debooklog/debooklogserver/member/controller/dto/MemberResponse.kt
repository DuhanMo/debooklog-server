package org.debooklog.debooklogserver.member.controller.dto

import org.debooklog.debooklogserver.member.domain.Member
import org.debooklog.debooklogserver.member.domain.SocialProvider
import java.time.LocalDateTime

data class MemberResponse(
    val id: Long?,
    val name: String,
    val email: String,
    val socialId: String,
    val provider: SocialProvider,
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime,
) {
    constructor(member: Member) : this(
        id = member.id,
        name = member.name,
        email = member.email,
        socialId = member.socialId,
        provider = member.provider,
        createdAt = member.createdAt,
        updatedAt = member.updatedAt,
    )
}
