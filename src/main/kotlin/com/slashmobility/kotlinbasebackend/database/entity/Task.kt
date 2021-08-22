package com.slashmobility.kotlinbasebackend.database.entity

import com.fasterxml.jackson.annotation.JsonIgnore
import javax.persistence.*

@Entity
@Table(name = "tasks")
data class Task(

    val description: String,
    @JsonIgnore
    @ManyToOne
    var employee: Employee
) {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long ? = null
}

