package org.debooklog.adapter.client.book.kakao.api

import org.debooklog.adapter.client.book.kakao.dto.KakaoBookInformationResponse
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.http.HttpHeaders.AUTHORIZATION
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestParam

@FeignClient(value = "kakao-daum-search-api", url = "\${kakao.daum.search-url}")
interface KakaoDaumSearchApi {
    @GetMapping("/v3/search/book")
    fun searchBook(
        @RequestHeader(AUTHORIZATION) apiKey: String,
        @RequestParam("query") query: String,
    ): KakaoBookInformationResponse
}
