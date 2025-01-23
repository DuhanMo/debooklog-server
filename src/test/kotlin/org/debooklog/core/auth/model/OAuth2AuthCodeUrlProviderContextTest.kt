package org.debooklog.core.auth.model

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.string.shouldContain
import org.debooklog.core.member.model.SocialProvider.GOOGLE
import org.debooklog.core.member.model.SocialProvider.KAKAO
import org.debooklog.mock.FakeGoogleOAuth2AuthCodeUrlProvider

class OAuth2AuthCodeUrlProviderContextTest : BehaviorSpec({
    Given("지원하는 provider가 있는 경우") {
        val fakeGoogleOAuth2AuthCodeUrlProvider = FakeGoogleOAuth2AuthCodeUrlProvider("http://redirect.url")
        val oAuth2AuthCodeUrlProviderContext =
            org.debooklog.core.auth.model.OAuth2AuthCodeUrlProviderContext(setOf(fakeGoogleOAuth2AuthCodeUrlProvider))

        When("redirect url을 요청 하면") {
            val redirectUrl = oAuth2AuthCodeUrlProviderContext.getRedirectUrl(GOOGLE, "keepState")

            Then("redirect url을 반환한다") {
                redirectUrl shouldContain "http://redirect.url"
            }
        }
    }

    Given("지원하는 provider가 없는 경우") {
        val fakeGoogleOAuth2AuthCodeUrlProvider = FakeGoogleOAuth2AuthCodeUrlProvider("http://redirect.url")
        val oAuth2AuthCodeUrlProviderContext =
            org.debooklog.core.auth.model.OAuth2AuthCodeUrlProviderContext(setOf(fakeGoogleOAuth2AuthCodeUrlProvider))
        When("redirect url을 요청 하면") {
            Then("예외 발생한다") {
                shouldThrow<IllegalArgumentException> {
                    oAuth2AuthCodeUrlProviderContext.getRedirectUrl(
                        KAKAO,
                        "keepState",
                    )
                }
            }
        }
    }
})
