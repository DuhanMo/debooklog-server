package org.debooklog.debooklogserver.auth.controller.dto

import org.debooklog.debooklogserver.member.domain.SocialProvider

data class OAuth2LoginRequest(val provider: SocialProvider, val code: String)
