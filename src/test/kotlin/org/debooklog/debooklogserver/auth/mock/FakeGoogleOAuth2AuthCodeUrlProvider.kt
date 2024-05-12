package org.debooklog.debooklogserver.auth.mock

import org.debooklog.debooklogserver.auth.domain.OAuth2AuthCodeUrlProviderStrategy
import org.debooklog.debooklogserver.member.domain.SocialProvider
import org.debooklog.debooklogserver.member.domain.SocialProvider.GOOGLE

class FakeGoogleOAuth2AuthCodeUrlProvider(
    private val stubRedirectUrl: String,
) : OAuth2AuthCodeUrlProviderStrategy {
    override val support: SocialProvider
        get() = GOOGLE

    override fun provide(state: String?): String {
        return "$stubRedirectUrl${state ?: ""}"
    }
}
