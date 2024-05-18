package org.debooklog.debooklogserver.auth.controller.dto

import org.debooklog.debooklogserver.auth.domain.TokenData

data class LoginResponse(
    val accessToken: String,
    val refreshToken: String,
) {
    companion object {
        fun from(tokenData: TokenData): LoginResponse {
            return LoginResponse(
                tokenData.accessToken,
                tokenData.refreshToken,
            )
        }
    }
}
