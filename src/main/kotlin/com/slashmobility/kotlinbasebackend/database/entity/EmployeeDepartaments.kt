package com.slashmobility.kotlinbasebackend.database.entity

import org.springframework.data.annotation.CreatedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.util.*
import javax.persistence.*

@Entity
@EntityListeners(AuditingEntityListener::class)
data class EmployeeDepartments(

    @ManyToOne @MapsId("employeeId")
    @JoinColumn(name = "employee_id")
    val employee: Employee,
    @ManyToOne
    @MapsId("departmentId")
    @JoinColumn(name = "department_id")
    val department: Department,

    ) {
    @Id
    @Column(name = "id", nullable = false)
    val id: Long? = null

    @CreatedDate
    val createdAt: Date? = null
    val endDateDate : Date? = null

}