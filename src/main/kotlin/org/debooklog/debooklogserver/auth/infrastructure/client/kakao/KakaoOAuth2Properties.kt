package org.debooklog.debooklogserver.auth.infrastructure.client.kakao

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties("oauth2.kakao")
data class KakaoOAuth2Properties(
    val clientId: String,
    val clientSecret: String,
    val redirectUri: String,
    val scope: Set<String>,
)
