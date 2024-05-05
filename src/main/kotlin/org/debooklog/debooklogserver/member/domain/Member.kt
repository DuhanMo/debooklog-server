package org.debooklog.debooklogserver.member.domain

import java.time.LocalDateTime

class Member(
    val id: Long?,
    val name: String,
    val socialId: String,
    val provider: SocialProvider,
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime,
)
