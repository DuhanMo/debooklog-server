package org.debooklog.adapter.client.auth.google.dto

import com.fasterxml.jackson.databind.PropertyNamingStrategies.SnakeCaseStrategy
import com.fasterxml.jackson.databind.annotation.JsonNaming
import org.debooklog.core.auth.model.OAuth2UserData
import org.debooklog.core.member.model.SocialProvider.GOOGLE

@JsonNaming(SnakeCaseStrategy::class)
data class GoogleUserInfoResponse(
    val sub: String,
    val name: String,
    val email: String,
    val givenName: String,
    val familyName: String?,
    val picture: String,
    val emailVerified: Boolean,
) {
    fun toOAuth2UserData(): OAuth2UserData {
        return OAuth2UserData(GOOGLE, sub, email, name, picture)
    }
}
