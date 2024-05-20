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
import org.hibernate.annotations.SQLDelete
import org.hibernate.annotations.SQLRestriction
import java.time.LocalDateTime

@SQLDelete(sql = "update members set is_deleted = true, deleted_at = now() where id = ?")
@SQLRestriction("is_deleted = false")
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
    @Column(name = "deleted_at")
    var deleteAt: LocalDateTime?,
    @Column(name = "is_deleted")
    var isDeleted: Boolean,
) : BaseEntity<MemberEntity>() {
    companion object {
        fun from(member: Member): MemberEntity {
            return MemberEntity(
                name = member.name,
                email = member.email,
                socialId = member.socialId,
                provider = member.provider,
                deleteAt = null,
                isDeleted = false,
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
