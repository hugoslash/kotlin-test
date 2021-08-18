package com.slashmobility.kotlinbasebackend.service.implementation

import com.slashmobility.kotlinbasebackend.database.entity.Employee
import com.slashmobility.kotlinbasebackend.database.repository.EmployeeRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Primary
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

@Primary
@Service
class JwtUserDetailsServiceImpl : UserDetailsService {
    @Autowired
    lateinit var employeeRepository: EmployeeRepository

    override fun loadUserByUsername(email: String?): UserDetails {
        var employee: Employee? = email?.let { employeeRepository.findByEmail(it) }
            ?: throw UsernameNotFoundException(String.format("No user found with username '%s'.", email))


        val grantedAuthorities = employee!!.roles
            .map { role -> SimpleGrantedAuthority(role.role?.name) }
            .toMutableList()
        var UserDetails = org.springframework.security.core.userdetails.User(employee.email,
            employee.password, grantedAuthorities)
        return UserDetails
    }
}