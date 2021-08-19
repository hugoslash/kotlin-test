package com.slashmobility.kotlinbasebackend.service.implementation

import com.slashmobility.kotlinbasebackend.database.repository.EmployeeRepository
import com.slashmobility.kotlinbasebackend.dto.request.LoginRequest
import com.slashmobility.kotlinbasebackend.dto.response.LoginResponse
import com.slashmobility.kotlinbasebackend.security.JwtTokenUtil
import com.slashmobility.kotlinbasebackend.service.interfaces.LoginService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException


@Service
class LoginServiceImpl : LoginService {
    @Autowired
    lateinit var authenticationManager: AuthenticationManager

    @Autowired
    lateinit var jwtTokenUtil: JwtTokenUtil

    @Autowired
    lateinit var employeeRepository: EmployeeRepository

    @Autowired
    lateinit var userDetailsService: UserDetailsService

    @Throws(UsernameNotFoundException::class, ResponseStatusException::class)
    override fun login(request: LoginRequest): LoginResponse {

        if (request.email?.let { employeeRepository.existsByEmail(it) } == false){
            throw ResponseStatusException(HttpStatus.NOT_FOUND, "entity not found")
        }

        val authentication = authenticationManager.authenticate(
            UsernamePasswordAuthenticationToken(
                request.email,
                request.password)
        )

        SecurityContextHolder.getContext().authentication = authentication

        val userDetails = userDetailsService.loadUserByUsername(request.email)
        val token = jwtTokenUtil.generateToken(userDetails)
        val employee = request.email?.let { employeeRepository.findByEmail(it) }

        return LoginResponse(userDetails.username, token, employee!!.roles.map { role -> role }.toSet())

    }
}