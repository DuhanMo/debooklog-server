package org.debooklog.adapter.persistence.member

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.EnumType.STRING
import jakarta.persistence.Enumerated
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType.IDENTITY
import jakarta.persistence.Id
import jakarta.persistence.PostPersist
import jakarta.persistence.Table
import org.debooklog.adapter.persistence.common.BaseEntity
import org.debooklog.core.member.model.Member
import org.debooklog.core.member.model.SocialProvider
import org.debooklog.core.member.service.MemberCreatedEvent
import org.hibernate.annotations.SQLRestriction
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import java.time.Instant

@SQLRestriction("is_deleted = false")
@Entity
@Table(name = "member")
class MemberEntity(
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id")
    val id: Long = 0,
    @Column(name = "name")
    val name: String,
    @Column(name = "email")
    val email: String,
    @Column(name = "social_id")
    val socialId: String,
    @Enumerated(STRING)
    @Column(name = "provider")
    val provider: SocialProvider,
    @Column(name = "profile_image")
    val profileImage: String?,
    @CreatedDate
    @Column(name = "created_at")
    var createdAt: Instant = Instant.MAX,
    @LastModifiedDate
    @Column(name = "updated_at")
    var updatedAt: Instant = Instant.MAX,
    @Column(name = "deleted_at")
    val deletedAt: Instant?,
    @Column(name = "is_deleted")
    val isDeleted: Boolean,
) : BaseEntity<MemberEntity>() {
    constructor(member: Member) : this(
        id = member.id,
        name = member.name,
        email = member.email,
        socialId = member.socialId,
        provider = member.provider,
        profileImage = member.profileImage,
        createdAt = member.createdAt,
        updatedAt = member.updatedAt,
        deletedAt = null,
        isDeleted = false,
    )

    @PostPersist
    fun onPostPersist() {
        registerEvent(MemberCreatedEvent(id))
    }

    fun toModel(): Member {
        return Member(
            id = id,
            name = name,
            email = email,
            socialId = socialId,
            provider = provider,
            profileImage = profileImage,
            createdAt = createdAt,
            updatedAt = updatedAt,
            deletedAt = deletedAt,
            isDeleted = isDeleted,
        )
    }
}
