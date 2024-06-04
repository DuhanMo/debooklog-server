package org.debooklog.debooklogserver.auth.controller.dto

import org.debooklog.debooklogserver.auth.domain.TokenData

data class LoginResponse(
    val accessToken: String,
    val refreshToken: String,
) {
    constructor(tokenData: TokenData) : this(
        accessToken = tokenData.accessToken,
        refreshToken = tokenData.refreshToken,
    )
}
