package com.slashmobility.kotlinbasebackend.database.entity

import javax.persistence.*

@Entity
@Table(name = "roles")
data class Role(

    val name: String
) {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: Long ? = null

}