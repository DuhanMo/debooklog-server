package org.debooklog.debooklogserver.auth.infrastructure.client.google

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties("oauth2.google")
data class GoogleOAuth2Properties(
    val clientId: String,
    val clientSecret: String,
    val redirectUri: String,
    val scope: Set<String>,
)
