package com.slashmobility.kotlinbasebackend.database.repository;

import com.slashmobility.kotlinbasebackend.database.entity.Employee
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface EmployeeRepository : JpaRepository<Employee, Long> {
    fun findById(it: String): Employee
    fun findByEmail(it: String) : Employee

}