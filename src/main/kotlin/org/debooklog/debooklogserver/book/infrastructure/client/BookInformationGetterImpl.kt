package org.debooklog.debooklogserver.book.infrastructure.client

import org.debooklog.debooklogserver.book.domain.BookInformationData
import org.debooklog.debooklogserver.book.infrastructure.client.kakao.KakaoBookInformationClient
import org.debooklog.debooklogserver.book.service.port.BookInformationGetter
import org.springframework.stereotype.Component

@Component
class BookInformationGetterImpl(
    private val kakaoBookInformationClient: KakaoBookInformationClient,
) : BookInformationGetter {
    override fun search(title: String): List<BookInformationData> {
        return kakaoBookInformationClient
            .search(title)
            .documents
            .map {
                BookInformationData(
                    title = it.title ?: "",
                    author = it.authors?.joinToString() ?: "",
                    isbn = it.isbn?.split(",")?.map { isbn -> isbn.trim() } ?: emptyList(),
                    thumbnail = it.thumbnail ?: "",
                )
            }
    }
}
