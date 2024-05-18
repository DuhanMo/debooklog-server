package org.debooklog.debooklogserver.auth.domain

data class TokenData(
    val accessToken: String,
    val refreshToken: String,
)
