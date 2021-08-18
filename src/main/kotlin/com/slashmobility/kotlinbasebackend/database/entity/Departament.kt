package com.slashmobility.kotlinbasebackend.database.entity

import org.springframework.data.annotation.CreatedDate
import java.util.*
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "departaments")
data class Departament(
    val name: String,

) {
    @Id
    @Column(name = "id", nullable = false)
    val id: Long? = null
    @CreatedDate
    val createdDate : Date? = null
}