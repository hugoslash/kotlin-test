package com.slashmobility.kotlinbasebackend.controller.auth

import com.slashmobility.kotlinbasebackend.dto.request.LoginRequest
import com.slashmobility.kotlinbasebackend.service.interfaces.LoginService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.security.core.AuthenticationException
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api")
class AuthController {


    @Autowired
    lateinit var loginService : LoginService;

    @PostMapping("login")
    @Throws(AuthenticationException::class)
    fun createAuthenticationToken(@RequestBody authenticationRequest: LoginRequest): ResponseEntity<*> {

        return ResponseEntity.ok(loginService.login(authenticationRequest))
    }

}