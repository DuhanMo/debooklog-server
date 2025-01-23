package org.debooklog.mock

import org.debooklog.core.auth.model.OAuth2UserData
import org.debooklog.core.auth.model.OAuth2UserDataGetterStrategy
import org.debooklog.core.member.model.SocialProvider
import org.debooklog.core.member.model.SocialProvider.GOOGLE

class FakeGoogleOAuth2UserDataGetter(
    private val stubOAuth2UserData: OAuth2UserData,
) : OAuth2UserDataGetterStrategy {
    override val support: SocialProvider
        get() = GOOGLE

    override fun fetch(code: String): OAuth2UserData {
        return stubOAuth2UserData
    }
}
