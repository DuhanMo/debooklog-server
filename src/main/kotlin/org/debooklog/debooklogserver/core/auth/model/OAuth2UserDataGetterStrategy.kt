package org.debooklog.debooklogserver.core.auth.model

import org.debooklog.debooklogserver.core.member.model.SocialProvider

interface OAuth2UserDataGetterStrategy {
    val support: SocialProvider

    fun fetch(code: String): OAuth2UserData
}
