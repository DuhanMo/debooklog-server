package org.debooklog.debooklogserver.adapter.controller.auth

import org.debooklog.debooklogserver.core.auth.model.TokenData

data class LoginResponse(
    val accessToken: String,
    val refreshToken: String,
) {
    constructor(tokenData: TokenData) : this(
        accessToken = tokenData.accessToken,
        refreshToken = tokenData.refreshToken,
    )
}
