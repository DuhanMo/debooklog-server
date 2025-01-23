package org.debooklog.core.auth.model

import org.debooklog.core.member.model.SocialProvider

interface OAuth2UserDataGetterStrategy {
    val support: SocialProvider

    fun fetch(code: String): OAuth2UserData
}
