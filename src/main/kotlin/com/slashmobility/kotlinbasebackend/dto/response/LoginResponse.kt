package com.slashmobility.kotlinbasebackend.dto.response

import com.slashmobility.kotlinbasebackend.database.entity.Role

class LoginResponse (var username: String? = null,
                     var token: String? = null,
                     var roles: Set<Role>){
}