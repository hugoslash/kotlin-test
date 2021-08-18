package com.slashmobility.kotlinbasebackend.dto.request

data class LoginRequest (var email: String? = null,
                    var password: String? = null)