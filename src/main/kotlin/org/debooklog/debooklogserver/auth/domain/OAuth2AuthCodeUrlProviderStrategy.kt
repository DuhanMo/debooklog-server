package org.debooklog.debooklogserver.auth.domain

import org.debooklog.debooklogserver.core.member.model.SocialProvider

interface OAuth2AuthCodeUrlProviderStrategy {
    val support: SocialProvider

    fun provide(state: String?): String
}
