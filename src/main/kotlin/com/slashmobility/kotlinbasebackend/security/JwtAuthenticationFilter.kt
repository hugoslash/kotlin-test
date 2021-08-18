package com.slashmobility.kotlinbasebackend.security

import org.apache.commons.logging.LogFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource
import org.springframework.web.filter.OncePerRequestFilter
import java.io.IOException
import javax.servlet.FilterChain
import javax.servlet.ServletException
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class JwtAuthenticationFilter {
    class JwtAuthenticationTokenFilter: OncePerRequestFilter() {
        private val log = LogFactory.getLog(this.javaClass)

        @Autowired
        lateinit var userDetailsService: UserDetailsService

        @Autowired
        lateinit var jwtTokenUtil: JwtTokenUtil

        @Value("\${jwt.header}")
        private val tokenHeader: String? = null

        @Throws(ServletException::class, IOException::class)
        override fun doFilterInternal(request: HttpServletRequest, response: HttpServletResponse, chain: FilterChain) {
            var authToken: String? = request.getHeader(this.tokenHeader)

            if (authToken != null && authToken.startsWith("Bearer ")) {
                authToken = authToken.substring(7)
            }

            val username = authToken?.let { jwtTokenUtil.getUsernameFromToken(it) }

            if (username != null && SecurityContextHolder.getContext().authentication == null) {

                val userDetails = this.userDetailsService.loadUserByUsername(username)

                if (authToken?.let { jwtTokenUtil.validateToken(it, userDetails) }!!) {
                    val authentication = UsernamePasswordAuthenticationToken(userDetails, null, userDetails.authorities)
                    authentication.details = WebAuthenticationDetailsSource().buildDetails(request)
                    log.info("authenticated user $username, setting security context")
                    SecurityContextHolder.getContext().authentication = authentication
                }
            }

            chain.doFilter(request, response)
        }
    }
}