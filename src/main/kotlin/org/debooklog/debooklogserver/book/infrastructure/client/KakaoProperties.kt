package org.debooklog.debooklogserver.book.infrastructure.client

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties("kakao")
data class KakaoProperties(
    val apiKey: String,
)
