package org.debooklog.adapter.client.auth.kakao

import org.debooklog.core.auth.model.OAuth2AuthCodeUrlProviderStrategy
import org.springframework.stereotype.Component
import org.springframework.web.util.UriComponentsBuilder

@Component
class KakaoOAuth2AuthCodeUrlProvider(
    private val properties: KakaoOAuth2Properties,
) : OAuth2AuthCodeUrlProviderStrategy {
    override val support: org.debooklog.core.member.model.SocialProvider
        get() = org.debooklog.core.member.model.SocialProvider.KAKAO

    override fun provide(state: String?): String {
        return UriComponentsBuilder
            .fromUriString("https://kauth.kakao.com/oauth/authorize")
            .queryParam("response_type", "code")
            .queryParam("client_id", properties.clientId)
            .queryParam("redirect_uri", properties.redirectUri)
            .queryParam("scope", properties.scope.joinToString(","))
            .queryParam("state", state)
            .toUriString()
    }
}
