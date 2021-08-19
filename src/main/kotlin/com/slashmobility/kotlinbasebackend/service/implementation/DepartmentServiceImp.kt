package com.slashmobility.kotlinbasebackend.service.implementation

import com.slashmobility.kotlinbasebackend.database.entity.Department
import com.slashmobility.kotlinbasebackend.database.repository.DepartmentRepository
import com.slashmobility.kotlinbasebackend.service.interfaces.DepartmentService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class DepartmentServiceImp  : DepartmentService {

    @Autowired
    lateinit var departmentRepository: DepartmentRepository

    override fun getDepartments(): List<Department> = departmentRepository.findAll()
}