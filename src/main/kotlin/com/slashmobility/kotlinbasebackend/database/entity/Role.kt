package com.slashmobility.kotlinbasebackend.database.entity

import com.fasterxml.jackson.annotation.JsonIgnore
import javax.persistence.*

@Entity
@Table(name = "roles")
data class Role(

    val name: String
) {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long ? = null

    @JsonIgnore
    @ManyToMany(mappedBy = "roles", )
    lateinit var employees: Set<Employee>
}