package com.slashmobility.kotlinbasebackend.service.implementation

import com.slashmobility.kotlinbasebackend.database.repository.EmployeeRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Primary
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

@Primary
@Service
class JwtUserDetailsServiceImpl : UserDetailsService {
    @Autowired
    lateinit var userRepository: EmployeeRepository

    override fun loadUserByUsername(username: String?): UserDetails {
        //var user = username?.let { userRepository.findByUsername(it) }
        //if (user == null){
        //    throw UsernameNotFoundException(String.format("No user found with username '%s'.", username))
        //}

        val UserDetails = org.springframework.security.core.userdetails.User("hola","hola", null)
        return UserDetails
    }
}