package org.debooklog.debooklogserver.adapter.client.auth.kakao.dto

import com.fasterxml.jackson.databind.PropertyNamingStrategies.SnakeCaseStrategy
import com.fasterxml.jackson.databind.annotation.JsonNaming
import org.debooklog.debooklogserver.core.auth.model.OAuth2UserData
import org.debooklog.debooklogserver.core.member.model.SocialProvider

@JsonNaming(value = SnakeCaseStrategy::class)
data class KakaoUserInfoResponse(
    val id: Long,
    val connectedAt: String,
    val properties: Properties,
    val kakaoAccount: KakaoAccount,
) {
    fun toOAuth2UserData(): OAuth2UserData {
        return OAuth2UserData(
            SocialProvider.KAKAO,
            id.toString(),
            kakaoAccount.email,
            properties.nickname,
            properties.profileImage,
        )
    }

    @JsonNaming(value = SnakeCaseStrategy::class)
    data class Properties(
        val nickname: String,
        val profileImage: String?,
    )

    @JsonNaming(value = SnakeCaseStrategy::class)
    data class KakaoAccount(
        val profileNicknameNeedsAgreement: Boolean,
        val profile: Profile,
        val hasEmail: Boolean,
        val emailNeedsAgreement: Boolean,
        val isEmailValid: Boolean,
        val isEmailVerified: Boolean,
        val email: String,
    )

    @JsonNaming(value = SnakeCaseStrategy::class)
    data class Profile(
        val nickname: String,
        val isDefaultNickname: Boolean,
    )
}
