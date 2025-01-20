package org.debooklog.debooklogserver.auth.domain

import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import org.debooklog.debooklogserver.auth.mock.FakeGoogleOAuth2UserDataGetter
import org.debooklog.debooklogserver.core.member.model.SocialProvider.GOOGLE

class OAuth2UserDataGetterContextTest : BehaviorSpec({

    lateinit var sut: OAuth2UserDataGetterContext

    Given("인증코드가 정상인 경우") {
        val getter =
            FakeGoogleOAuth2UserDataGetter(
                stubOAuth2UserData =
                    OAuth2UserData(
                        provider = GOOGLE,
                        id = "123123",
                        email = "test@gmail.com",
                        nickname = "구글길동",
                        profileImage = "imageUrl.com",
                    ),
            )
        sut = OAuth2UserDataGetterContext(setOf(getter))

        When("해당 코드를 통해 유저정보를 요청하는 경우") {
            val actual = sut.getOAuth2UserData(GOOGLE, "인증코드")

            Then("유저정보를 반환한다") {
                actual.id shouldBe "123123"
                actual.nickname shouldBe "구글길동"
            }
        }
    }
})
