package org.debooklog.debooklogserver.member.domain

import org.debooklog.debooklogserver.auth.domain.OAuth2UserData
import java.time.LocalDateTime
import java.time.LocalDateTime.now

data class Member(
    val id: Long?,
    val name: String,
    val email: String,
    val socialId: String,
    val provider: SocialProvider,
    val profileImage: String?,
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime,
    val deletedAt: LocalDateTime?,
    val isDeleted: Boolean,
) {
    constructor(oAuth2UserData: OAuth2UserData) : this(
        id = null,
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
