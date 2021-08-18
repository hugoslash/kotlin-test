package com.slashmobility.kotlinbasebackend.database.entity

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id

@Entity
data class Role(
    @Id
    @Column(name = "id", nullable = false)
    val id: Long,
    val name: String
) {
}