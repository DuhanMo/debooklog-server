package org.debooklog.debooklogserver.member.domain

import jakarta.persistence.Entity
import org.debooklog.debooklogserver.common.domain.BaseEntity

@Entity
class Member(
    val name: String,
    val socialId: String,
    val provider: SocialProvider,
) : BaseEntity()
