package com.slashmobility.kotlinbasebackend.database.repository

import com.slashmobility.kotlinbasebackend.database.entity.Department
import com.slashmobility.kotlinbasebackend.database.entity.Role
import org.springframework.data.jpa.repository.JpaRepository

interface DepartmentRepository : JpaRepository<Department, Long> {
    fun existsByName(name: String): Boolean
    fun findByName(s: String): Role
}