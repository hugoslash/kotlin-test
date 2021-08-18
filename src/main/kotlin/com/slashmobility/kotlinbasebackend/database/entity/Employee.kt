package com.slashmobility.kotlinbasebackend.database.entity

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import javax.persistence.*
import kotlin.jvm.Transient

@Entity
@Table(name = "employees")
data class Employee(

    var firstname: String? = null,
    var lastname: String? = null,
    @Column(unique=true)
    var email: String,
    private var password: String,


): UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    var id: Long? = null

    @Column(nullable = false, unique = true)
    @OneToMany(mappedBy = "employee")
    lateinit var roles: Set<EmployeeRoles>

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
            grantedAuthorities = roles
                .map { role -> SimpleGrantedAuthority(role.role.name) }
                .toMutableList()
            return grantedAuthorities
        }

    }
}