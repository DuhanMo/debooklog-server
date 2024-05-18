package org.debooklog.debooklogserver.auth.controller.port

import org.debooklog.debooklogserver.auth.domain.TokenData
import org.debooklog.debooklogserver.member.domain.SocialProvider

interface OAuth2Service {
    fun getRedirectUrl(
        provider: SocialProvider,
        state: String?,
    ): String

    fun loginByAuthCode(
        provider: SocialProvider,
        code: String,
    ): TokenData
}
