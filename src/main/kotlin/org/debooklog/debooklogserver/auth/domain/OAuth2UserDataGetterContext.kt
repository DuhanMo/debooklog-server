package org.debooklog.debooklogserver.auth.domain

import org.debooklog.debooklogserver.member.domain.SocialProvider
import org.springframework.stereotype.Component

@Component
class OAuth2UserDataGetterContext(
    strategies: Set<OAuth2UserDataGetterStrategy>,
) {
    private val strategies = strategies.associateBy { it.support }

    fun getOAuth2UserData(
        provider: SocialProvider,
        code: String,
    ): OAuth2UserData {
        return strategies[provider]
            ?.fetch(code)
            ?: throw IllegalArgumentException()
    }
}
