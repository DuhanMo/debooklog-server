package org.debooklog.debooklogserver.adapter.client.auth.google

import org.debooklog.debooklogserver.adapter.client.auth.google.dto.GoogleUserInfoResponse
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.http.HttpHeaders
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestHeader

@FeignClient(
    name = "google-client",
    url = "\${google.api-url}",
)
interface GoogleClient {
    @GetMapping("/oauth2/v3/userinfo")
    fun fetchUserInfo(
        @RequestHeader(name = HttpHeaders.AUTHORIZATION) accessToken: String,
    ): GoogleUserInfoResponse
}
