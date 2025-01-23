package org.debooklog.adapter.controller.auth

import org.debooklog.core.member.model.SocialProvider

data class OAuth2LoginRequest(val provider: SocialProvider, val code: String)
