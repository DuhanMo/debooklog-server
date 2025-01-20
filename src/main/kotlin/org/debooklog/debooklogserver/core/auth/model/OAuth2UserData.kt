package org.debooklog.debooklogserver.core.auth.model

import org.debooklog.debooklogserver.core.member.model.SocialProvider

data class OAuth2UserData(
    val provider: SocialProvider,
    val id: String,
    val email: String,
    val nickname: String,
    val profileImage: String?,
)
