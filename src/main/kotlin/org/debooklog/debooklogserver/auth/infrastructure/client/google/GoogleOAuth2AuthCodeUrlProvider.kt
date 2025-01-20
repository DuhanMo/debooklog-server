package org.debooklog.debooklogserver.auth.infrastructure.client.google

import org.debooklog.debooklogserver.auth.domain.OAuth2AuthCodeUrlProviderStrategy
import org.debooklog.debooklogserver.core.member.model.SocialProvider
import org.springframework.stereotype.Component
import org.springframework.web.util.UriComponentsBuilder

@Component
class GoogleOAuth2AuthCodeUrlProvider(
    private val properties: GoogleOAuth2Properties,
) : OAuth2AuthCodeUrlProviderStrategy {
    override val support: SocialProvider
        get() = SocialProvider.GOOGLE

    override fun provide(state: String?): String {
        return UriComponentsBuilder
            .fromUriString("https://accounts.google.com/o/oauth2/v2/auth")
            .queryParam("response_type", "code")
            .queryParam("client_id", properties.clientId)
            .queryParam("redirect_uri", properties.redirectUri)
            .queryParam("scope", properties.scope.joinToString(" "))
            .queryParam("state", state)
            .build()
            .toUriString()
    }
}
