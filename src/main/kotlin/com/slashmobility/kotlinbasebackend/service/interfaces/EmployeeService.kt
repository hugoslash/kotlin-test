package com.slashmobility.kotlinbasebackend.service.interfaces

import com.slashmobility.kotlinbasebackend.dto.request.EmployeeSelfValueRequest
import com.slashmobility.kotlinbasebackend.dto.request.EmployeeValueRequest
import com.slashmobility.kotlinbasebackend.dto.response.EmployeeResponse

interface EmployeeService {
    fun selfValue(request: EmployeeSelfValueRequest) : EmployeeResponse
    fun value(request: EmployeeValueRequest) : EmployeeResponse
}