package org.debooklog.debooklogserver.auth.domain

import org.debooklog.debooklogserver.member.domain.SocialProvider

interface OAuth2AuthCodeUrlProviderStrategy {
    val support: SocialProvider

    fun provide(state: String?): String
}
