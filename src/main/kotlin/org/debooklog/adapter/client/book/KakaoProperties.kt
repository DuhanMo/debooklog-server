package org.debooklog.adapter.client.book

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties("kakao")
data class KakaoProperties(
    val apiKey: String,
)
