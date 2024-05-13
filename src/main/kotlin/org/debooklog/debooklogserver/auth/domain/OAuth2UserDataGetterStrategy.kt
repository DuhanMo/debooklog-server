package org.debooklog.debooklogserver.auth.domain

import org.debooklog.debooklogserver.member.domain.SocialProvider

interface OAuth2UserDataGetterStrategy {
    val support: SocialProvider

    fun fetch(code: String): OAuth2UserData
}
