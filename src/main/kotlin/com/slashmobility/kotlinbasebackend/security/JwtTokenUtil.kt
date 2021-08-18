package com.slashmobility.kotlinbasebackend.security

import com.slashmobility.kotlinbasebackend.database.entity.Employee
import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Component
import java.util.*
import kotlin.collections.HashMap

@Component
class JwtTokenUtil {
    private val CLAIM_KEY_USERNAME = "sub"
    private val CLAIM_KEY_AUDIENCE:String = "audience"
    private val CLAIM_KEY_CREATED = "created"
    private val AUDIENCE_UNKNOWN = "unknown"
    private val AUDIENCE_WEB = "web"
    private val AUDIENCE_MOBILE = "mobile"
    private val AUDIENCE_TABLET = "tablet"
    @Value("\${jwt.secret}")
    var secret: String? = null


    @Value("\${jwt.expiration}")
    var expiration: Long? = null

    fun getUsernameFromToken(token: String): String? {
        var username: String?
        try {
            val claims = getClaimsFromToken(token)
            username = claims?.subject
        } catch (e: Exception) {
            username = null
        }

        return username
    }

    fun getCreatedDateFromToken(token: String): Date? {
        var created: Date?
        try {
            val claims = getClaimsFromToken(token)
            created = Date(claims?.get(CLAIM_KEY_CREATED) as Long)
        } catch (e: Exception) {
            created = null
        }

        return created
    }

    fun getExpirationDateFromToken(token: String): Date? {
        var expiration: Date?
        try {
            val claims = getClaimsFromToken(token)
            expiration = claims?.expiration
        } catch (e: Exception) {
            expiration = null
        }

        return expiration
    }

    fun getAudienceFromToken(token: String): String? {
        var audience: String?
        try {
            val claims = getClaimsFromToken(token)
            audience = claims?.get(CLAIM_KEY_AUDIENCE) as String
        } catch (e: Exception) {
            audience = null
        }

        return audience
    }

    private fun getClaimsFromToken(token: String?): Claims? {
        var claims: Claims?
        if (token == null){
            return null
        }
        try {
            claims = Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .body
        } catch (e: Exception) {
            claims = null
        }

        return claims
    }

    private fun generateExpirationDate(): Date {
        return Date(System.currentTimeMillis() + (expiration?.times(1000) ?: 0))
    }

    private fun isTokenExpired(token: String): Boolean {
        val expiration = getExpirationDateFromToken(token)
        return expiration!!.before(Date())
    }

    private fun isCreatedBeforeLastPasswordReset(created: Date?, lastPasswordReset: Date?): Boolean {
        return lastPasswordReset != null && created!!.before(lastPasswordReset)
    }


    private fun ignoreTokenExpiration(token: String): Boolean {
        val audience = getAudienceFromToken(token)
        return AUDIENCE_TABLET == audience || AUDIENCE_MOBILE == audience
    }

    fun generateToken(userDetails: UserDetails, ): String {
        val claims = HashMap<String, Any>()
        claims[CLAIM_KEY_USERNAME] = userDetails.username
        claims[CLAIM_KEY_CREATED] = Date()
        return generateToken(claims)
    }

    internal fun generateToken(claims: Map<String, Any>): String {
        return Jwts.builder()
            .setClaims(claims)
            .setExpiration(generateExpirationDate())
            .signWith(SignatureAlgorithm.HS512, secret)
            .compact()
    }

    fun canTokenBeRefreshed(token: String, lastPasswordReset: Date?): Boolean {
        val created = getCreatedDateFromToken(token)
        return !isCreatedBeforeLastPasswordReset(created, lastPasswordReset) && (!isTokenExpired(token) || ignoreTokenExpiration(token))
    }

    fun refreshToken(token: String?): String? {
        var refreshedToken: String?
        try {
            val claims = getClaimsFromToken(token)
            claims?.put(CLAIM_KEY_CREATED, Date())
            refreshedToken = claims?.let { generateToken(it) }
        } catch (e: Exception) {
            refreshedToken = null
        }

        return refreshedToken
    }

    fun validateToken(token: String, userDetails: UserDetails): Boolean? {
        val user = userDetails as Employee
        val username = getUsernameFromToken(token)
        val created = getCreatedDateFromToken(token)
        //final Date expiration = getExpirationDateFromToken(token);
        return (username == user.email
                && !isTokenExpired(token)
                )
    }
}
