package org.debooklog.core.member.model

import org.debooklog.core.auth.model.OAuth2UserData
import java.time.Instant
import java.time.Instant.now

data class Member(
    val id: Long,
    val name: String,
    val email: String,
    val socialId: String,
    val provider: SocialProvider,
    val profileImage: String?,
    val createdAt: Instant,
    val updatedAt: Instant,
    val deletedAt: Instant?,
    val isDeleted: Boolean,
) {
    constructor(oAuth2UserData: OAuth2UserData) : this(
        id = 0,
        name = oAuth2UserData.nickname,
        email = oAuth2UserData.email,
        socialId = oAuth2UserData.id,
        provider = oAuth2UserData.provider,
        profileImage = oAuth2UserData.profileImage,
        createdAt = now(),
        updatedAt = now(),
        deletedAt = null,
        isDeleted = false,
    )

    fun withdrawal(): Member {
        return this.copy(deletedAt = now(), isDeleted = true)
    }
}
