package org.debooklog.core.auth.service

import org.debooklog.core.auth.model.OAuth2AuthCodeUrlProviderContext
import org.debooklog.core.auth.model.OAuth2UserDataGetterContext
import org.debooklog.core.auth.model.TokenData
import org.debooklog.core.auth.port.JwtProvider
import org.debooklog.core.member.model.Member
import org.debooklog.core.member.model.SocialProvider
import org.debooklog.core.member.port.MemberRepository
import org.springframework.stereotype.Service

@Service
class OAuth2Service(
    private val oAuth2AuthCodeUrlProviderContext: OAuth2AuthCodeUrlProviderContext,
    private val oAuth2UserDataGetterContext: OAuth2UserDataGetterContext,
    private val jwtProvider: JwtProvider,
    private val memberRepository: MemberRepository,
) {
    fun getRedirectUrl(
        provider: SocialProvider,
        state: String?,
    ): String {
        return oAuth2AuthCodeUrlProviderContext.getRedirectUrl(provider, state)
    }

    fun loginByAuthCode(
        provider: SocialProvider,
        code: String,
    ): TokenData {
        val oAuth2UserData = oAuth2UserDataGetterContext.getOAuth2UserData(provider, code)
        memberRepository.findByEmail(oAuth2UserData.email)?.let {
            memberRepository.save(Member(oAuth2UserData))
        }
        return TokenData(
            jwtProvider.createAccessJwt(oAuth2UserData.email),
            jwtProvider.createRefreshJwt(oAuth2UserData.email),
        )
    }
}
