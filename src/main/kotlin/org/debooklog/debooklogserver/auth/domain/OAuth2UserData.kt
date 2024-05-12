package org.debooklog.debooklogserver.auth.domain

import org.debooklog.debooklogserver.member.domain.SocialProvider

data class OAuth2UserData(val provider: SocialProvider, val id: String, val nickname: String)
