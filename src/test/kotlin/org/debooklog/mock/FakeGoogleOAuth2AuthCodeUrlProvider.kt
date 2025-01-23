package org.debooklog.mock

import org.debooklog.core.auth.model.OAuth2AuthCodeUrlProviderStrategy
import org.debooklog.core.member.model.SocialProvider
import org.debooklog.core.member.model.SocialProvider.GOOGLE

class FakeGoogleOAuth2AuthCodeUrlProvider(
    private val stubRedirectUrl: String,
) : OAuth2AuthCodeUrlProviderStrategy {
    override val support: SocialProvider
        get() = GOOGLE

    override fun provide(state: String?): String {
        return "$stubRedirectUrl${state ?: ""}"
    }
}
