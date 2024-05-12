package org.debooklog.debooklogserver.member.infrastructure.persistence

import jakarta.persistence.Entity
import jakarta.persistence.PostPersist
import org.debooklog.debooklogserver.common.domain.BaseEntity
import org.debooklog.debooklogserver.member.domain.Member
import org.debooklog.debooklogserver.member.domain.MemberCreatedEvent
import org.debooklog.debooklogserver.member.domain.SocialProvider

@Entity
class MemberEntity(
    val name: String,
    val socialId: String,
    val provider: SocialProvider,
) : BaseEntity<MemberEntity>() {
    companion object {
        fun from(member: Member): MemberEntity {
            return MemberEntity(
                name = member.name,
                socialId = member.socialId,
                provider = member.provider,
            )
        }
    }

    fun toModel(): Member {
        return Member(
            id = id,
            name = name,
            socialId = socialId,
            provider = provider,
            createdAt = createdAt,
            updatedAt = updatedAt,
        )
    }

    @PostPersist
    fun onPostPersist() {
        println(">>> id is $id")
        registerEvent(MemberCreatedEvent(id ?: throw IllegalStateException("id must not be null")))
    }
}
