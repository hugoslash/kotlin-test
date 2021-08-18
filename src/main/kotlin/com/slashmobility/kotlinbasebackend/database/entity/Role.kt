package com.slashmobility.kotlinbasebackend.database.entity

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "roles")
data class Role(
    @Id
    @Column(name = "id", nullable = false)
    val id: Long,
    val name: String
) {
}