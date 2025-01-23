package org.debooklog.adapter.client.book

import org.debooklog.adapter.client.book.kakao.api.KakaoDaumSearchApi
import org.debooklog.adapter.client.book.kakao.dto.KakaoBookInformationResponse
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
