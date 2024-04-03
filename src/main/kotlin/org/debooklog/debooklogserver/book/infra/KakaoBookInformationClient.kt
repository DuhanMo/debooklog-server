package org.debooklog.debooklogserver.book.infra

import org.debooklog.debooklogserver.book.domain.BookInformationData
import org.debooklog.debooklogserver.book.domain.BookInformationGetter
import org.debooklog.debooklogserver.book.infra.api.KakaoDaumSearchApi
import org.springframework.stereotype.Component

@Component
class KakaoBookInformationClient(
    private val kakaoDaumSearchApi: KakaoDaumSearchApi,
    private val kakaoProperties: KakaoProperties,
) : BookInformationGetter {
    override fun search(title: String): List<BookInformationData> {
        val response =
            kakaoDaumSearchApi.searchBook(
                apiKey = "KakaoAK ${kakaoProperties.apiKey}",
                query = title,
            )
        return response.documents.map {
            BookInformationData(
                title = it.title ?: "",
                author = it.authors?.joinToString() ?: "",
                isbn = it.isbn ?: "",
                thumbnail = it.thumbnail ?: "",
            )
        }
    }
}
