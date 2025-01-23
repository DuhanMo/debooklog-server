package org.debooklog.adapter.security

import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jws
import io.jsonwebtoken.JwtException
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.io.Decoders
import io.jsonwebtoken.security.Keys
import org.debooklog.core.auth.port.JwtProvider
import org.springframework.stereotype.Component
import java.util.Date
import javax.crypto.SecretKey

@Component
class JwtProviderImpl(
    private val properties: JwtProperties,
) : JwtProvider {
    override fun createAccessJwt(payload: String): String {
        val claims: Claims = Jwts.claims().setSubject(payload)
        val now = Date()
        val expiration = Date(now.time + properties.accessExpiration)
        return Jwts.builder()
            .setClaims(claims)
            .setIssuedAt(now)
            .setExpiration(expiration)
            .signWith(getSignInKey())
            .compact()
    }

    override fun createRefreshJwt(payload: String): String {
        val claims: Claims = Jwts.claims().setSubject(payload)
        val now = Date()
        val expiration = Date(now.time + properties.refreshExpiration)
        return Jwts.builder()
            .setClaims(claims)
            .setIssuedAt(now)
            .setExpiration(expiration)
            .signWith(getSignInKey())
            .compact()
    }

    override fun getSubject(jwt: String): String =
        getClaimsJws(jwt)
            .body
            .subject

    override fun isValidJwt(jwt: String): Boolean {
        return try {
            getClaimsJws(jwt)
            true
        } catch (e: JwtException) {
            false
        } catch (e: IllegalArgumentException) {
            false
        }
    }

    private fun getSignInKey(): SecretKey {
        val keyBytes = Decoders.BASE64.decode(properties.secret)
        return Keys.hmacShaKeyFor(keyBytes)
    }

    private fun getClaimsJws(jwt: String): Jws<Claims> =
        Jwts.parserBuilder()
            .setSigningKey(getSignInKey().encoded)
            .build()
            .parseClaimsJws(jwt)
}
