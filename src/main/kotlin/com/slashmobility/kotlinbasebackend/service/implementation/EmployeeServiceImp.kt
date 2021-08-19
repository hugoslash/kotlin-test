package com.slashmobility.kotlinbasebackend.service.implementation

import com.slashmobility.kotlinbasebackend.database.repository.EmployeeRepository
import com.slashmobility.kotlinbasebackend.dto.request.EmployeeSelfValueRequest
import com.slashmobility.kotlinbasebackend.dto.request.EmployeeValueRequest
import com.slashmobility.kotlinbasebackend.dto.response.EmployeeResponse
import com.slashmobility.kotlinbasebackend.service.interfaces.EmployeeService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException

@Service
class EmployeeServiceImp  : EmployeeService {
    @Autowired
    lateinit var employeeRepository : EmployeeRepository

    override fun selfValue(request: EmployeeSelfValueRequest): EmployeeResponse {
        val employee = employeeRepository.findByEmail(SecurityContextHolder.getContext().authentication.name)
        employee.selfValue = request.value
        return EmployeeResponse(employee.id,employee.email,employee.firstname,employee.selfValue,employee.value,employee.salary)
    }

    override fun value(request: EmployeeValueRequest): EmployeeResponse {
        val optionalEmployee = employeeRepository.findById(request.employee)
        if (optionalEmployee.isEmpty){
            throw ResponseStatusException(HttpStatus.NOT_FOUND, "entity not found")
        }
        optionalEmployee.get().value = request.value
        return EmployeeResponse(optionalEmployee.get().id,optionalEmployee.get().email,optionalEmployee.get().firstname,optionalEmployee.get().selfValue,optionalEmployee.get().value,optionalEmployee.get().salary)
    }




}