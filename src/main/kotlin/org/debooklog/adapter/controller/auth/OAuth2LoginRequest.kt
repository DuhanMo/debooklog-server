package org.debooklog.adapter.controller.auth

data class OAuth2LoginRequest(val provider: org.debooklog.core.member.model.SocialProvider, val code: String)
