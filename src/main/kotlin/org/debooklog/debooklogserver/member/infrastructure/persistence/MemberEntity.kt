package org.debooklog.debooklogserver.member.infrastructure.persistence

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.EnumType.STRING
import jakarta.persistence.Enumerated
import jakarta.persistence.PostPersist
import jakarta.persistence.Table
import org.debooklog.debooklogserver.common.domain.BaseEntity
import org.debooklog.debooklogserver.member.domain.Member
import org.debooklog.debooklogserver.member.domain.MemberCreatedEvent
import org.debooklog.debooklogserver.member.domain.SocialProvider

@Entity
@Table(name = "members")
class MemberEntity(
    @Column(name = "name")
    val name: String,
    @Column(name = "email")
    val email: String,
    @Column(name = "social_id")
    val socialId: String,
    @Enumerated(STRING)
    @Column(name = "provider")
    val provider: SocialProvider,
) : BaseEntity<MemberEntity>() {
    companion object {
        fun from(member: Member): MemberEntity {
            return MemberEntity(
                name = member.name,
                email = member.email,
                socialId = member.socialId,
                provider = member.provider,
            )
        }
    }

    fun toModel(): Member {
        return Member(
            id = id,
            name = name,
            email = email,
            socialId = socialId,
            provider = provider,
            createdAt = createdAt,
            updatedAt = updatedAt,
        )
    }

    @PostPersist
    fun onPostPersist() {
        registerEvent(MemberCreatedEvent(id ?: throw IllegalStateException("id must not be null")))
    }
}
