package com.slashmobility.kotlinbasebackend.service.interfaces

import com.slashmobility.kotlinbasebackend.database.entity.Department

interface DepartmentService {
    fun getDepartments() : List<Department>
}