package org.debooklog.debooklogserver.auth.infrastructure.client.google.dto

import com.fasterxml.jackson.databind.PropertyNamingStrategies.SnakeCaseStrategy
import com.fasterxml.jackson.databind.annotation.JsonNaming

@JsonNaming(SnakeCaseStrategy::class)
data class GoogleToken(
    val accessToken: String,
    val expiresIn: Int,
    val scope: String,
    val tokenType: String,
)
