package org.debooklog.debooklogserver.book.infra

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties("kakao")
data class KakaoProperties(
    val apiKey: String,
)
