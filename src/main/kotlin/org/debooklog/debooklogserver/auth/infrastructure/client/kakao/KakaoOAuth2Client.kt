package org.debooklog.debooklogserver.auth.infrastructure.client.kakao

import feign.Headers
import org.debooklog.debooklogserver.auth.infrastructure.client.kakao.dto.KakaoToken
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.http.MediaType.APPLICATION_FORM_URLENCODED_VALUE
import org.springframework.web.bind.annotation.PostMapping

@FeignClient(
    name = "kakao-oauth2-client",
    url = "\${kakao.oauth2-token-url}",
    configuration = [FormFeignEncoderConfig::class],
)
interface KakaoOAuth2Client {
    @PostMapping(value = ["/oauth2/token"], consumes = [APPLICATION_FORM_URLENCODED_VALUE])
    @Headers("Content-Type: application/x-www-form-urlencoded")
    fun fetchToken(form: Map<String, String>): KakaoToken
}
