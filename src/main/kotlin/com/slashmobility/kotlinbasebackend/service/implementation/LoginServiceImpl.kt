package com.slashmobility.kotlinbasebackend.service.implementation

import com.slashmobility.kotlinbasebackend.dto.request.LoginRequest
import com.slashmobility.kotlinbasebackend.dto.response.LoginResponse
import com.slashmobility.kotlinbasebackend.security.JwtTokenUtil
import com.slashmobility.kotlinbasebackend.service.interfaces.LoginService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service

@Service
class LoginServiceImpl : LoginService {
    @Value("\${jwt.header}")
    private val tokenHeader: String? = null

    @Autowired
    lateinit var authenticationManager: AuthenticationManager

    @Autowired
    lateinit var jwtTokenUtil: JwtTokenUtil

    @Autowired
    lateinit var userDetailsService: UserDetailsService

    override fun login(request: LoginRequest): LoginResponse {

        val authentication = authenticationManager.authenticate(
            UsernamePasswordAuthenticationToken(
                request.email,
                request.password)
        )

        SecurityContextHolder.getContext().authentication = authentication

        val userDetails = userDetailsService.loadUserByUsername(request.email)
        val token = jwtTokenUtil.generateToken(userDetails)

        return LoginResponse(userDetails.username, token)
    }
}