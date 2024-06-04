package org.debooklog.debooklogserver.auth.service

import org.debooklog.debooklogserver.auth.controller.port.OAuth2Service
import org.debooklog.debooklogserver.auth.domain.OAuth2AuthCodeUrlProviderContext
import org.debooklog.debooklogserver.auth.domain.OAuth2UserDataGetterContext
import org.debooklog.debooklogserver.auth.domain.TokenData
import org.debooklog.debooklogserver.common.security.JwtProvider
import org.debooklog.debooklogserver.member.domain.Member
import org.debooklog.debooklogserver.member.domain.SocialProvider
import org.debooklog.debooklogserver.member.service.port.MemberRepository
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
class OAuth2ServiceImpl(
    private val oAuth2AuthCodeUrlProviderContext: OAuth2AuthCodeUrlProviderContext,
    private val oAuth2UserDataGetterContext: OAuth2UserDataGetterContext,
    private val jwtProvider: JwtProvider,
    private val memberRepository: MemberRepository,
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
    ): TokenData {
        val oAuth2UserData = oAuth2UserDataGetterContext.getOAuth2UserData(provider, code)
        val member = memberRepository.findByEmail(oAuth2UserData.email)
        if (member == null) {
            memberRepository.save(Member(oAuth2UserData))
        }
        return TokenData(
            jwtProvider.createAccessJwt(oAuth2UserData.email),
            jwtProvider.createRefreshJwt(oAuth2UserData.email),
        )
    }

    companion object {
        private val logger = LoggerFactory.getLogger(OAuth2ServiceImpl::class.java)
    }
}
