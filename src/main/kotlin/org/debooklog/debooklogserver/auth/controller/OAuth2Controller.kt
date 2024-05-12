package org.debooklog.debooklogserver.auth.controller

import jakarta.servlet.http.HttpServletResponse
import org.debooklog.debooklogserver.auth.controller.dto.OAuth2LoginRequest
import org.debooklog.debooklogserver.auth.controller.port.OAuth2Service
import org.debooklog.debooklogserver.member.domain.SocialProvider
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/oauth2")
class OAuth2Controller(
    private val oAuth2Service: OAuth2Service,
) {
    @GetMapping("/code/{provider}")
    fun redirectGetAuthCodePage(
        @PathVariable provider: SocialProvider,
        @RequestParam(name = "state", required = false) state: String?,
        response: HttpServletResponse,
    ) {
        val targetUrl = oAuth2Service.getRedirectUrl(provider, state)
        response.sendRedirect(targetUrl)
    }

    @GetMapping("/callback/{provider}")
    fun redirectCodeAndStateToClient(
        @PathVariable provider: SocialProvider,
        @RequestParam(name = "code", required = true) code: String,
        @RequestParam(name = "state", required = false) state: String?,
        response: HttpServletResponse,
    ) {
        println("code = $code")
        println("state = $state")
        response.sendRedirect(getTargetUrl(code, state, provider))
    }

    @PostMapping("/login")
    fun loginByAuthCode(
        @RequestBody request: OAuth2LoginRequest,
    ) {
        oAuth2Service.loginByAuthCode(request.provider, request.code)
    }

    private fun getTargetUrl(
        code: String,
        state: String?,
        provider: SocialProvider,
    ) = "http://localhost:3000?code=$code&state=${state ?: ""}&provider=$provider"
}
