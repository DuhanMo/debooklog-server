package org.debooklog.core.auth.model

import org.debooklog.core.member.model.SocialProvider

interface OAuth2AuthCodeUrlProviderStrategy {
    val support: SocialProvider

    fun provide(state: String?): String
}
