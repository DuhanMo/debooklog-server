package org.debooklog.debooklogserver.global.security

import io.jsonwebtoken.ClaimJwtException
import io.jsonwebtoken.Claims
import io.jsonwebtoken.JwtException
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import io.jsonwebtoken.io.Decoders
import io.jsonwebtoken.security.Keys
import org.debooklog.debooklogserver.member.domain.Member
import org.springframework.stereotype.Component
import java.util.Date
import javax.crypto.SecretKey

@Component
class JwtProvider(
    private val properties: JwtProperties,
) {
    fun generateAccessJwt(member: Member): String {
        return generateJwt(member, properties.accessExpiration)
    }

    fun generateRefreshJwt(member: Member): String {
        return generateJwt(member, properties.refreshExpiration)
    }

    fun extractUserId(jwt: String): Long {
        return extractClaim(jwt, Claims::getSubject).toLong()
    }

    fun isValidJwt(jwt: String): Boolean {
        return try {
            extractClaim(jwt, Claims::getExpiration)
            true
        } catch (e: JwtException) {
            false
        } catch (e: IllegalArgumentException) {
            false
        }
    }

    private fun generateJwt(
        member: Member,
        expiration: Long,
    ): String {
        return Jwts.builder().setSubject(member.id.toString()).setIssuedAt(Date(System.currentTimeMillis()))
            .setExpiration(Date(System.currentTimeMillis() + expiration))
            .signWith(getSignInKey(), SignatureAlgorithm.HS256).compact()
    }

    private fun <T> extractClaim(
        jwt: String,
        claimsResolver: (Claims) -> T,
    ): T {
        val claims = extractAllClaims(jwt)
        return claimsResolver.invoke(claims)
    }

    private fun extractAllClaims(jwt: String): Claims {
        return try {
            Jwts.parserBuilder().setSigningKey(getSignInKey()).build().parseClaimsJws(jwt).body
        } catch (e: ClaimJwtException) {
            throw e
        }
    }

    private fun getSignInKey(): SecretKey {
        val keyBytes = Decoders.BASE64.decode(properties.secret)
        return Keys.hmacShaKeyFor(keyBytes)
    }
}
