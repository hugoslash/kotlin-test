package com.slashmobility.kotlinbasebackend.service.interfaces

import com.slashmobility.kotlinbasebackend.dto.request.LoginRequest
import com.slashmobility.kotlinbasebackend.dto.response.LoginResponse

interface LoginService {

    fun login(request: LoginRequest) : LoginResponse
}