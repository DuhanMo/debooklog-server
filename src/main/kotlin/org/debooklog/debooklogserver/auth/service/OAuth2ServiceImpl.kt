package org.debooklog.debooklogserver.auth.service

import org.debooklog.debooklogserver.auth.controller.port.OAuth2Service
import org.debooklog.debooklogserver.auth.domain.OAuth2AuthCodeUrlProviderContext
import org.debooklog.debooklogserver.auth.domain.OAuth2UserDataGetterContext
import org.debooklog.debooklogserver.member.domain.SocialProvider
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
class OAuth2ServiceImpl(
    private val oAuth2AuthCodeUrlProviderContext: OAuth2AuthCodeUrlProviderContext,
    private val oAuth2UserDataGetterContext: OAuth2UserDataGetterContext,
) : OAuth2Service {
    override fun getRedirectUrl(
        provider: SocialProvider,
        state: String?,
    ): String {
        return oAuth2AuthCodeUrlProviderContext.getRedirectUrl(provider, state)
    }

    override fun loginByAuthCode(
        provider: SocialProvider,
        code: String,
    ) {
        logger.info("login 시도 provider: $provider, code: $code")
        val oAuth2UserData = oAuth2UserDataGetterContext.getOAuth2UserData(provider, code)
        logger.info("oAuth2UserData: $oAuth2UserData")
        // todo token 응답
    }

    companion object {
        private val logger = LoggerFactory.getLogger(OAuth2ServiceImpl::class.java)
    }
}
