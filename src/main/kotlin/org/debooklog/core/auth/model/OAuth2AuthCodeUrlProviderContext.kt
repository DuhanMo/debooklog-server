package org.debooklog.core.auth.model

import org.debooklog.core.member.model.SocialProvider
import org.springframework.stereotype.Component

// todo: 클래스 위치 변경. 도메인에서 제외
@Component
class OAuth2AuthCodeUrlProviderContext(
    strategies: Set<OAuth2AuthCodeUrlProviderStrategy>,
) {
    private val strategies = strategies.associateBy { it.support }

    fun getRedirectUrl(
        provider: SocialProvider,
        state: String?,
    ): String {
        return strategies[provider]
            ?.provide(state)
            ?: throw IllegalArgumentException()
    }
}
