package org.debooklog.debooklogserver.auth.infrastructure.client.kakao

import org.debooklog.debooklogserver.auth.domain.OAuth2UserData
import org.debooklog.debooklogserver.auth.domain.OAuth2UserDataGetterStrategy
import org.debooklog.debooklogserver.member.domain.SocialProvider
import org.debooklog.debooklogserver.member.domain.SocialProvider.KAKAO
import org.springframework.stereotype.Component
import org.springframework.util.LinkedMultiValueMap
import org.springframework.util.MultiValueMap

@Component
class KakaoOAuth2UserDataGetter(
    private val properties: KakaoOAuth2Properties,
    private val kakaoOAuth2Client: KakaoOAuth2Client,
) : OAuth2UserDataGetterStrategy {
    override val support: SocialProvider
        get() = KAKAO

    override fun fetch(code: String): OAuth2UserData {
        val form =
            mapOf(
                "grant_type" to "authorization_code",
                "client_id" to properties.clientId,
                "client_secret" to properties.clientSecret,
                "code" to code,
                "redirect_uri" to properties.redirectUri,
            )
        val fetchToken = kakaoOAuth2Client.fetchToken(form)
        return OAuth2UserData(KAKAO, "", "")
    }

    private fun tokenRequestParam(code: String): MultiValueMap<String, String> {
        val params: MultiValueMap<String, String> = LinkedMultiValueMap()
        params.add("grant_type", "authorization_code")
        params.add("client_id", properties.clientId)
        params.add("client_secret", properties.clientSecret)
        params.add("code", code)
        params.add("redirect_uri", properties.redirectUri)
        return params
    }
}
