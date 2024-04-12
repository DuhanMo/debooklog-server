package org.debooklog.debooklogserver.global.security

import io.jsonwebtoken.JwtException
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.debooklog.debooklogserver.member.domain.MemberRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.http.HttpHeaders.AUTHORIZATION
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType.APPLICATION_JSON_VALUE
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.context.SecurityContextHolder.getContext
import org.springframework.web.filter.OncePerRequestFilter

class JwtAuthenticationFilter(
    private val memberRepository: MemberRepository,
    private val jwtProvider: JwtProvider,
) : OncePerRequestFilter() {
    companion object {
        private const val BEARER_TYPE = "Bearer"
    }

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain,
    ) {
        logger.info("JwtAuthenticationFilter 활성화")
        try {
            setSecurityContextIfAuthorizationExist(request)
            filterChain.doFilter(request, response)
        } catch (e: JwtException) {
            logger.error(e)
            response.contentType = APPLICATION_JSON_VALUE
            response.sendError(HttpStatus.UNAUTHORIZED.value(), e.message)
        }
    }

    private fun setSecurityContextIfAuthorizationExist(request: HttpServletRequest) {
        val authorization: String? = request.getHeader(AUTHORIZATION)
        if (authorization == null || !authorization.lowercase().startsWith(BEARER_TYPE.lowercase())) {
            return
        }
        val jwt = extractJwt(authorization)
        logger.info("jwt is $jwt !!")
        val member = memberRepository.findByIdOrNull(jwtProvider.extractUserId(jwt))
        if (member != null && getContext().authentication == null) {
            logger.info("Security Context set user")
            getContext().authentication =
                UsernamePasswordAuthenticationToken(
                    member, null, listOf(SimpleGrantedAuthority("ROLE_USER")),
                )
        }
    }

    private fun extractJwt(authorization: String): String = authorization.substring(BEARER_TYPE.length).trim()
}
