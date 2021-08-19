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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    var id: Long? = null

    @ManyToMany(cascade = [CascadeType.ALL])
    @JoinTable(name = "employee_roles")
    lateinit var roles: Set<Role>

    var value: Int ? = null
    var autoValue: Int ? = null
    var salary: Float ?= null

    @Transient
    var grantedAuthorities: MutableCollection<out GrantedAuthority> = mutableListOf()
    override fun isEnabled(): Boolean {
        return true
    }

    override fun getUsername(): String {
        return this.username
    }

    override fun isCredentialsNonExpired(): Boolean {
        return true
    }

    override fun getPassword(): String {
        return password
    }

    override fun isAccountNonExpired(): Boolean {
        return true
    }

    override fun isAccountNonLocked(): Boolean {
        return true
    }
    override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
        return if (grantedAuthorities.isNotEmpty() ){
            grantedAuthorities
        }else{
            grantedAuthorities = roles
                .map { role -> SimpleGrantedAuthority(role.name) }
                .toMutableList()
            grantedAuthorities
        }

    }
}