package org.debooklog.debooklogserver.auth.infrastructure.client.kakao.dto

import com.fasterxml.jackson.databind.PropertyNamingStrategies.SnakeCaseStrategy
import com.fasterxml.jackson.databind.annotation.JsonNaming
import org.debooklog.debooklogserver.auth.domain.OAuth2UserData
import org.debooklog.debooklogserver.member.domain.SocialProvider
import java.time.LocalDateTime

@JsonNaming(value = SnakeCaseStrategy::class)
data class KakaoUserInfoResponse(
    val id: Long,
    val connectedAt: LocalDateTime,
    val properties: KakaoUserProperties,
    val kakaoAccount: KakaoAccount,
) {
    fun toOAuth2UserData(): OAuth2UserData {
        return OAuth2UserData(SocialProvider.KAKAO, id.toString(), properties.nickname)
    }

    @JsonNaming(value = SnakeCaseStrategy::class)
    data class KakaoUserProperties(
        val nickname: String,
        val profileImage: String?,
    )

    @JsonNaming(value = SnakeCaseStrategy::class)
    data class KakaoAccount(
        val profileNicknameNeedsAgreement: Boolean,
        val profile: KakaoAccountProfile,
    )

    @JsonNaming(value = SnakeCaseStrategy::class)
    data class KakaoAccountProfile(
        val nickname: String,
    )
}
