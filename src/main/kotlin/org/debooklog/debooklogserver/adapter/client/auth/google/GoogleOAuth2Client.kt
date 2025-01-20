package org.debooklog.debooklogserver.adapter.client.auth.google

import org.debooklog.debooklogserver.adapter.client.auth.google.dto.GoogleToken
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.http.MediaType.APPLICATION_FORM_URLENCODED_VALUE
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam

@FeignClient(
    name = "google-oauth2-client",
    url = "\${google.oauth2-token-url}",
)
interface GoogleOAuth2Client {
    @PostMapping(value = ["/token"], consumes = [APPLICATION_FORM_URLENCODED_VALUE])
    fun fetchToken(
        @RequestParam("grant_type") grantType: String,
        @RequestParam("client_id") clientId: String,
        @RequestParam("client_secret") clientSecret: String,
        @RequestParam("code") code: String,
        @RequestParam("redirect_uri") redirectUri: String,
    ): GoogleToken
}
