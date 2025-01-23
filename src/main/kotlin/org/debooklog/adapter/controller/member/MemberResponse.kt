package org.debooklog.adapter.controller.member

import org.debooklog.core.member.model.Member
import java.time.LocalDateTime

data class MemberResponse(
    val id: Long?,
    val name: String,
    val email: String,
    val socialId: String,
    val provider: org.debooklog.core.member.model.SocialProvider,
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
