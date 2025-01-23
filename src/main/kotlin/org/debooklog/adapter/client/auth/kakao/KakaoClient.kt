package org.debooklog.adapter.client.auth.kakao

import org.debooklog.adapter.client.auth.kakao.dto.KakaoUserInfoResponse
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.http.HttpHeaders
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestHeader

@FeignClient(
    name = "kakao-client",
    url = "\${kakao.api-url}",
)
interface KakaoClient {
    @GetMapping("/v2/user/me")
    fun fetchUserInfo(
        @RequestHeader(name = HttpHeaders.AUTHORIZATION) accessToken: String,
    ): KakaoUserInfoResponse
}
