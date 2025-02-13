package org.debooklog.adapter.controller.member

import org.debooklog.core.member.model.Member
import org.debooklog.core.member.model.SocialProvider
import java.time.Instant

data class MemberResponse(
    val id: Long?,
    val name: String,
    val email: String,
    val socialId: String,
    val provider: SocialProvider,
    val createdAt: Instant,
    val updatedAt: Instant,
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
