package com.slashmobility.kotlinbasebackend.database.entity

import org.springframework.data.annotation.CreatedDate
import org.springframework.data.jpa.repository.config.EnableJpaAuditing
import java.time.LocalDateTime
import java.util.*
import javax.persistence.*

@Entity
@EnableJpaAuditing
@Table(name = "departaments")
data class Departament(
    val name: String,
    @CreatedDate
    @Column(name = "created_date", nullable = false, updatable = false)
    val createdDate : LocalDateTime? = null
) {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: Long? = null

}