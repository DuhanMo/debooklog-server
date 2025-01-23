package org.debooklog.core.auth.port

interface JwtProvider {
    fun createAccessJwt(payload: String): String

    fun createRefreshJwt(payload: String): String

    fun getSubject(jwt: String): String

    fun isValidJwt(jwt: String): Boolean
}
