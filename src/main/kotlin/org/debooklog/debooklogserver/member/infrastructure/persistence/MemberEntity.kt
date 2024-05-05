package org.debooklog.debooklogserver.member.infrastructure.persistence

import jakarta.persistence.Entity
import org.debooklog.debooklogserver.common.domain.BaseEntity
import org.debooklog.debooklogserver.member.domain.SocialProvider

@Entity
class MemberEntity(
    val name: String,
    val socialId: String,
    val provider: SocialProvider,
) : BaseEntity()
