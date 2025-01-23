package org.debooklog.adapter.client.auth.kakao

import org.debooklog.core.auth.model.OAuth2UserData
import org.debooklog.core.auth.model.OAuth2UserDataGetterStrategy
import org.debooklog.core.member.model.SocialProvider.KAKAO
import org.springframework.stereotype.Component

@Component
class KakaoOAuth2UserDataGetter(
    private val properties: KakaoOAuth2Properties,
    private val kakaoOAuth2Client: KakaoOAuth2Client,
    private val kakaoClient: KakaoClient,
) : OAuth2UserDataGetterStrategy {
    override val support: org.debooklog.core.member.model.SocialProvider
        get() = KAKAO

    override fun fetch(code: String): OAuth2UserData {
        val kakaoToken =
            kakaoOAuth2Client.fetchToken(
                grantType = "authorization_code",
                clientId = properties.clientId,
                clientSecret = properties.clientSecret,
                code = code,
                redirectUri = properties.redirectUri,
            )
        return kakaoClient.fetchUserInfo("Bearer ${kakaoToken.accessToken}")
            .toOAuth2UserData()
    }
}
