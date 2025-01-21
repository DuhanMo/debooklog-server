package org.debooklog.debooklogserver.core.auth.service

import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.string.shouldContain
import org.debooklog.debooklogserver.adapter.security.JwtProperties
import org.debooklog.debooklogserver.adapter.security.JwtProviderImpl
import org.debooklog.debooklogserver.core.auth.model.OAuth2AuthCodeUrlProviderContext
import org.debooklog.debooklogserver.core.auth.model.OAuth2UserData
import org.debooklog.debooklogserver.core.auth.model.OAuth2UserDataGetterContext
import org.debooklog.debooklogserver.core.member.model.SocialProvider.GOOGLE
import org.debooklog.debooklogserver.mock.FakeGoogleOAuth2AuthCodeUrlProvider
import org.debooklog.debooklogserver.mock.FakeGoogleOAuth2UserDataGetter
import org.debooklog.debooklogserver.mock.FakeMemberRepository

class OAuth2ServiceTest : BehaviorSpec({

    lateinit var sut: OAuth2Service

    Given("AuthCodeProvider가 정상 redirect url 반환하는 경우") {
        sut =
            OAuth2Service(
                oAuth2AuthCodeUrlProviderContext =
                    OAuth2AuthCodeUrlProviderContext(
                        setOf(
                            FakeGoogleOAuth2AuthCodeUrlProvider("http://redirect.url"),
                        ),
                    ),
                oAuth2UserDataGetterContext =
                    OAuth2UserDataGetterContext(
                        setOf(
                            FakeGoogleOAuth2UserDataGetter(
                                OAuth2UserData(
                                    GOOGLE,
                                    "123456",
                                    "test@gmail.com",
                                    "구글길동",
                                    "imageUrl.com",
                                ),
                            ),
                        ),
                    ),
                jwtProvider = JwtProviderImpl(JwtProperties("sercet", 2000L, 2000L)),
                memberRepository = FakeMemberRepository(),
            )
        When("getRedirectUrl을 호출하면") {
            val actual = sut.getRedirectUrl(GOOGLE, "keepState")

            Then("redirect url을 반환한다") {
                actual shouldContain "http://redirect.url"
            }
        }
    }
})
