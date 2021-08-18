package com.slashmobility.kotlinbasebackend.database.entity

import org.springframework.data.annotation.CreatedDate
import java.util.*
import javax.persistence.*

@Entity
data class EmployeeDepartaments(

    @ManyToOne @MapsId("employeeId")
    @JoinColumn(name = "employee_id")
    val employee: Employee,
    @ManyToOne
    @MapsId("departamentId")
    @JoinColumn(name = "departament_id")
    val departamentId: Departament,

) {
    @Id
    @Column(name = "id", nullable = false)
    val id: Long? = null

    @CreatedDate
    val createdAt: Date? = null
    val endDateDate : Date? = null

}