package org.debooklog.core.auth.model

interface OAuth2AuthCodeUrlProviderStrategy {
    val support: org.debooklog.core.member.model.SocialProvider

    fun provide(state: String?): String
}
