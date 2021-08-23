package com.slashmobility.kotlinbasebackend.service.interfaces

import com.slashmobility.kotlinbasebackend.dto.request.*
import com.slashmobility.kotlinbasebackend.dto.response.EmployeeResponse

interface EmployeeService {
    fun selfValue(request: EmployeeSelfValueRequest) : EmployeeResponse
    fun value(request: EmployeeValueRequest) : EmployeeResponse
    fun addTask(request: TaskRequest) : EmployeeResponse
    fun deleteById(id: Long): String
    fun createEmployee(request: CreateEmployeeRequest) : EmployeeResponse
    fun updateEmployee(id: Long,request: UpdateEmployeeRequest) : EmployeeResponse

}