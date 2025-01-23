package org.debooklog.core.auth.model

data class TokenData(
    val accessToken: String,
    val refreshToken: String,
)
