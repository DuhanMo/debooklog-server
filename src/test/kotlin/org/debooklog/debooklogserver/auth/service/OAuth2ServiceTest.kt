package org.debooklog.debooklogserver.auth.service

import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.string.shouldContain
import org.debooklog.debooklogserver.auth.domain.OAuth2AuthCodeUrlProviderContext
import org.debooklog.debooklogserver.auth.mock.FakeGoogleOAuth2AuthCodeUrlProvider
import org.debooklog.debooklogserver.member.domain.SocialProvider.GOOGLE

class OAuth2ServiceTest : BehaviorSpec({

    lateinit var sut: OAuth2ServiceImpl

    Given("AuthCodeProvider가 정상 redirect url 반환하는 경우") {
        sut =
            OAuth2ServiceImpl(
                oAuth2AuthCodeUrlProviderContext =
                    OAuth2AuthCodeUrlProviderContext(
                        setOf(
                            FakeGoogleOAuth2AuthCodeUrlProvider("http://redirect.url"),
                        ),
                    ),
            )
        When("getRedirectUrl을 호출하면") {
            val actual = sut.getRedirectUrl(GOOGLE, "keepState")

            Then("redirect url을 반환한다") {
                actual shouldContain "http://redirect.url"
            }
        }
    }
})
