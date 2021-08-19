package com.slashmobility.kotlinbasebackend.database.repository;

import com.slashmobility.kotlinbasebackend.database.entity.Employee
import org.springframework.data.jpa.repository.JpaRepository

interface EmployeeRepository : JpaRepository<Employee, Long> {
    fun findByEmail(it: String) : Employee
    fun existsByEmail(it: String) : Boolean

}