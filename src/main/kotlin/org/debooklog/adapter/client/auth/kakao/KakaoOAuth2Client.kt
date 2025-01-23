package org.debooklog.adapter.client.auth.kakao

import org.debooklog.adapter.client.auth.kakao.dto.KakaoToken
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.http.MediaType.APPLICATION_FORM_URLENCODED_VALUE
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam

@FeignClient(
    name = "kakao-oauth2-client",
    url = "\${kakao.oauth2-token-url}",
)
interface KakaoOAuth2Client {
    @PostMapping(value = ["/oauth/token"], consumes = [APPLICATION_FORM_URLENCODED_VALUE])
    fun fetchToken(
        @RequestParam("grant_type") grantType: String,
        @RequestParam("client_id") clientId: String,
        @RequestParam("client_secret") clientSecret: String,
        @RequestParam("code") code: String,
        @RequestParam("redirect_uri") redirectUri: String,
    ): KakaoToken
}
