package org.debooklog.debooklogserver.auth.controller.dto

import org.debooklog.debooklogserver.core.member.model.SocialProvider

data class OAuth2LoginRequest(val provider: SocialProvider, val code: String)
