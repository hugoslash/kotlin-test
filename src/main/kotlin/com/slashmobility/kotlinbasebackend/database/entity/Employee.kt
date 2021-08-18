package com.slashmobility.kotlinbasebackend.database.entity

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import javax.persistence.*
import kotlin.jvm.Transient

@Entity
data class Employee(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    var id: Long,
    var firstname: String? = null,
    var lastname: String? = null,
    var email: String? = null,

    @Column(nullable = false, unique = true)
    @OneToMany(mappedBy = "employee")
    var roles: Set<EmployeeRoles>
): UserDetails {

    @ManyToMany
    var authorities = mutableListOf<Authority>()
    //@OneToOne(mappedBy = "jwtUser")
    //var employee: Employee? = null

    @Transient
    var grantedAuthorities: MutableCollection<out GrantedAuthority> = mutableListOf()
    override fun isEnabled(): Boolean {
        return true
    }

    override fun getUsername(): String? {
        return username
    }

    override fun isCredentialsNonExpired(): Boolean {
        return true
    }

    override fun getPassword(): String? {
        return password
    }

    override fun isAccountNonExpired(): Boolean {
        return true
    }

    override fun isAccountNonLocked(): Boolean {
        return true
    }
    override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
        if (grantedAuthorities.size >0 ){
            return grantedAuthorities
        }else{
            grantedAuthorities = authorities
                .map { authority -> SimpleGrantedAuthority(authority.name?.name) }
                .toMutableList()
            return grantedAuthorities
        }

    }
}