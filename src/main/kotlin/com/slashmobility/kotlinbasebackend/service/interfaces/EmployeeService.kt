package com.slashmobility.kotlinbasebackend.service.interfaces

import com.slashmobility.kotlinbasebackend.dto.request.EmployeeValueRequest
import com.slashmobility.kotlinbasebackend.dto.response.EmployeeResponse

interface EmployeeService {
    fun selfValue(request: EmployeeValueRequest) : EmployeeResponse
}