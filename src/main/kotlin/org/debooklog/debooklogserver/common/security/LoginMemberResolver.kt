package org.debooklog.debooklogserver.common.security

import org.debooklog.debooklogserver.core.member.model.Member
import org.debooklog.debooklogserver.core.member.port.MemberRepository
import org.springframework.core.MethodParameter
import org.springframework.http.HttpHeaders.AUTHORIZATION
import org.springframework.stereotype.Component
import org.springframework.web.bind.support.WebDataBinderFactory
import org.springframework.web.context.request.NativeWebRequest
import org.springframework.web.method.support.HandlerMethodArgumentResolver
import org.springframework.web.method.support.ModelAndViewContainer

private const val BEARER = "Bearer"

@Component
class LoginMemberResolver(
    private val jwtProvider: JwtProvider,
    private val memberRepository: MemberRepository,
) : HandlerMethodArgumentResolver {
    override fun supportsParameter(parameter: MethodParameter): Boolean {
        return parameter.hasParameterAnnotation(LoginMember::class.java)
    }

    override fun resolveArgument(
        parameter: MethodParameter,
        mavContainer: ModelAndViewContainer?,
        webRequest: NativeWebRequest,
        binderFactory: WebDataBinderFactory?,
    ): Member {
        val jwt = extractJwt(webRequest)
        if (!jwtProvider.isValidJwt(jwt)) {
            throw LoginFailedException()
        }
        val email = jwtProvider.getSubject(jwt)
        return memberRepository.findByEmail(email)
            ?: throw LoginFailedException()
    }

    private fun extractJwt(webRequest: NativeWebRequest): String {
        val authorization = webRequest.getHeader(AUTHORIZATION) ?: throw LoginFailedException()
        val (tokenType, jwt) = splitAuthorization(authorization)
        if (tokenType != BEARER) {
            throw LoginFailedException()
        }
        return jwt
    }

    private fun splitAuthorization(authorization: String): Pair<String, String> {
        return try {
            val tokenFormat = authorization.split(" ")
            tokenFormat[0] to tokenFormat[1]
        } catch (e: IndexOutOfBoundsException) {
            throw LoginFailedException()
        }
    }
}
