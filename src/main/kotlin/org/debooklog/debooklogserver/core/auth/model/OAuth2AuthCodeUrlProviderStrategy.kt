package org.debooklog.debooklogserver.core.auth.model

import org.debooklog.debooklogserver.core.member.model.SocialProvider

interface OAuth2AuthCodeUrlProviderStrategy {
    val support: SocialProvider

    fun provide(state: String?): String
}
