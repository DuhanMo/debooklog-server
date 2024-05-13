package org.debooklog.debooklogserver.auth.infrastructure.client.kakao.dto

import com.fasterxml.jackson.databind.PropertyNamingStrategies.SnakeCaseStrategy
import com.fasterxml.jackson.databind.annotation.JsonNaming

@JsonNaming(SnakeCaseStrategy::class)
data class GetKakaoTokenRequest(
    val grantType: String,
    val clientId: String,
    val clientSecret: String,
    val code: String,
    val redirectUri: String,
)
