package org.debooklog.debooklogserver.member.domain

import org.debooklog.debooklogserver.auth.domain.OAuth2UserData
import java.time.LocalDateTime
import java.time.LocalDateTime.now

class Member(
    val id: Long?,
    val name: String,
    val email: String,
    val socialId: String,
    val provider: SocialProvider,
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime,
) {
    companion object {
        fun from(oAuth2UserData: OAuth2UserData): Member =
            Member(
                id = null,
                name = oAuth2UserData.nickname,
                email = oAuth2UserData.email,
                socialId = oAuth2UserData.id,
                provider = oAuth2UserData.provider,
                createdAt = now(),
                updatedAt = now(),
            )
    }
}
