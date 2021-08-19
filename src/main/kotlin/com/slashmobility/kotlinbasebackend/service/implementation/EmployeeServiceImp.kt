package com.slashmobility.kotlinbasebackend.service.implementation

import com.slashmobility.kotlinbasebackend.database.repository.EmployeeRepository
import com.slashmobility.kotlinbasebackend.dto.request.EmployeeValueRequest
import com.slashmobility.kotlinbasebackend.dto.response.EmployeeResponse
import com.slashmobility.kotlinbasebackend.service.interfaces.EmployeeService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Service

@Service
class EmployeeServiceImp  : EmployeeService {
    @Autowired
    lateinit var employeeRepository : EmployeeRepository

    override fun selfValue(request: EmployeeValueRequest): EmployeeResponse {
        val employee = employeeRepository.findByEmail(SecurityContextHolder.getContext().authentication.name)
        employee.selfValue = request.value
        return EmployeeResponse(employee.id,employee.email,employee.firstname,employee.selfValue,employee.value,employee.salary)
    }


}