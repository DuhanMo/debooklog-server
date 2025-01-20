package org.debooklog.debooklogserver.auth.mock

import org.debooklog.debooklogserver.core.auth.model.OAuth2AuthCodeUrlProviderStrategy
import org.debooklog.debooklogserver.core.member.model.SocialProvider
import org.debooklog.debooklogserver.core.member.model.SocialProvider.GOOGLE

class FakeGoogleOAuth2AuthCodeUrlProvider(
    private val stubRedirectUrl: String,
) : OAuth2AuthCodeUrlProviderStrategy {
    override val support: SocialProvider
        get() = GOOGLE

    override fun provide(state: String?): String {
        return "$stubRedirectUrl${state ?: ""}"
    }
}
