package org.debooklog.debooklogserver.member.infrastructure.persistence

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.EnumType.STRING
import jakarta.persistence.Enumerated
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType.IDENTITY
import jakarta.persistence.Id
import jakarta.persistence.PostPersist
import jakarta.persistence.Table
import org.debooklog.debooklogserver.common.domain.BaseEntity
import org.debooklog.debooklogserver.member.domain.Member
import org.debooklog.debooklogserver.member.domain.MemberCreatedEvent
import org.debooklog.debooklogserver.member.domain.SocialProvider
import org.hibernate.annotations.SQLRestriction
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import java.time.LocalDateTime

@SQLRestriction("is_deleted = false")
@Entity
@Table(name = "members")
class MemberEntity(
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id")
    val id: Long? = null,
    @Column(name = "name")
    val name: String,
    @Column(name = "email")
    val email: String,
    @Column(name = "social_id")
    val socialId: String,
    @Enumerated(STRING)
    @Column(name = "provider")
    val provider: SocialProvider,
    @CreatedDate
    @Column(name = "created_at")
    var createdAt: LocalDateTime = LocalDateTime.MAX,
    @LastModifiedDate
    @Column(name = "updated_at")
    var updatedAt: LocalDateTime = LocalDateTime.MAX,
    @Column(name = "deleted_at")
    val deletedAt: LocalDateTime?,
    @Column(name = "is_deleted")
    val isDeleted: Boolean,
) : BaseEntity<MemberEntity>() {
    constructor(member: Member) : this(
        id = member.id,
        name = member.name,
        email = member.email,
        socialId = member.socialId,
        provider = member.provider,
        createdAt = member.createdAt,
        updatedAt = member.updatedAt,
        deletedAt = null,
        isDeleted = false,
    )

    @PostPersist
    fun onPostPersist() {
        registerEvent(MemberCreatedEvent(id ?: throw IllegalStateException("id must not be null")))
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
            deletedAt = deletedAt,
            isDeleted = isDeleted,
        )
    }
}
