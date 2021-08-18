package com.slashmobility.kotlinbasebackend.database.entity

import java.util.*
import javax.persistence.*

@Entity
data class EmployeeRoles(
    @Id
    @Column(name = "id", nullable = false)
    val id: Long,
    @ManyToOne @MapsId("employeeId")
    @JoinColumn(name = "employee_id")
    val employee: Employee,
    @ManyToOne
    @MapsId("roleId")
    @JoinColumn(name = "role_id")
    val role: Role,
    val createdAt: Date
) {
}