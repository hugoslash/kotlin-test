package com.slashmobility.kotlinbasebackend.database.entity

import com.slashmobility.kotlinbasebackend.config.AuthorityName
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.ManyToMany

@Entity
data class Authority(var name: AuthorityName? = null) {
    @Id
    @GeneratedValue
    var id:Long? = null
    @ManyToMany(mappedBy = "authorities")
    var jwtUser = mutableListOf<Employee>()

}