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

    @ManyToMany(cascade = [CascadeType.ALL], fetch = FetchType.EAGER)
    @JoinTable(name = "employee_roles")
    lateinit var roles: Set<Role>

    var value: Int ? = null
    var selfValue: Int ? = null
    var salary: Float ?= null

    @OneToMany
    @MapsId("TaskId")
    @JoinColumn(name = "employee_id")
    var tasks: List<Task> ?= null

    @Transient
    var grantedAuthorities: MutableCollection<out GrantedAuthority> = mutableListOf()
    override fun isEnabled(): Boolean {
        return true
    }

    override fun getUsername(): String = this.username

    override fun isCredentialsNonExpired(): Boolean = true

    override fun getPassword(): String = this.password

    override fun isAccountNonExpired(): Boolean = true

    override fun isAccountNonLocked(): Boolean = true

    override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
        return grantedAuthorities.ifEmpty {
            grantedAuthorities = roles
                .map { role -> SimpleGrantedAuthority(role.name) }
                .toMutableList()
            grantedAuthorities
        }

    }
}