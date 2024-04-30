package org.debooklog.debooklogserver.book.infrastructure.client.kakao

import org.debooklog.debooklogserver.book.infrastructure.client.kakao.api.KakaoDaumSearchApi
import org.debooklog.debooklogserver.book.infrastructure.client.kakao.dto.KakaoBookInformationResponse
import org.springframework.stereotype.Component

@Component
class KakaoBookInformationClient(
    private val kakaoDaumSearchApi: KakaoDaumSearchApi,
    private val kakaoProperties: KakaoProperties,
) {
    fun search(title: String): KakaoBookInformationResponse {
        return kakaoDaumSearchApi.searchBook(
            apiKey = "KakaoAK ${kakaoProperties.apiKey}",
            query = title,
        )
    }
}
