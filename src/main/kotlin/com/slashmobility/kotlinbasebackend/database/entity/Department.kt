package com.slashmobility.kotlinbasebackend.database.entity

import org.springframework.data.annotation.CreatedDate
import org.springframework.data.jpa.repository.config.EnableJpaAuditing
import java.time.LocalDateTime
import javax.persistence.*

@Entity
@EnableJpaAuditing
@Table(name = "departments")
data class Department(
    val name: String,
    @CreatedDate
    @Column(name = "created_date", nullable = true, updatable = false)
    val createdDate : LocalDateTime? = null
) {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null

}