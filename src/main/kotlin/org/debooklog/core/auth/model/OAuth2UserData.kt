package org.debooklog.core.auth.model

data class OAuth2UserData(
    val provider: org.debooklog.core.member.model.SocialProvider,
    val id: String,
    val email: String,
    val nickname: String,
    val profileImage: String?,
)
