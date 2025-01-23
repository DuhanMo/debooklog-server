package org.debooklog.core.auth.model

interface OAuth2UserDataGetterStrategy {
    val support: org.debooklog.core.member.model.SocialProvider

    fun fetch(code: String): OAuth2UserData
}
