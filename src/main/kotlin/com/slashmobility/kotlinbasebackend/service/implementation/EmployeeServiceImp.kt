package com.slashmobility.kotlinbasebackend.service.implementation

import com.slashmobility.kotlinbasebackend.database.entity.Employee
import com.slashmobility.kotlinbasebackend.database.entity.Task
import com.slashmobility.kotlinbasebackend.database.repository.EmployeeRepository
import com.slashmobility.kotlinbasebackend.database.repository.TaskRepository
import com.slashmobility.kotlinbasebackend.dto.request.*
import com.slashmobility.kotlinbasebackend.dto.response.EmployeeResponse
import com.slashmobility.kotlinbasebackend.service.interfaces.EmployeeService
import javassist.NotFoundException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException
import java.util.*

@Service
class EmployeeServiceImp  : EmployeeService {
    @Autowired
    lateinit var employeeRepository : EmployeeRepository
    @Autowired
    lateinit var taskRepository : TaskRepository
    @Autowired
    lateinit var passwordEncoder: BCryptPasswordEncoder

    override fun selfValue(request: EmployeeSelfValueRequest): EmployeeResponse {
        val employee = employeeRepository.findByEmail(SecurityContextHolder.getContext().authentication.name)
        employee.selfValue = request.value
        employeeRepository.save(employee)
        return EmployeeResponse(employee.id,employee.email,employee.firstname,employee.selfValue,employee.value,employee.salary, employee.tasks)
    }

    override fun value(request: EmployeeValueRequest): EmployeeResponse {
        val optionalEmployee = employeeRepository.findById(request.employee)
        if (optionalEmployee.isEmpty){
            throw ResponseStatusException(HttpStatus.NOT_FOUND, "entity not found")
        }
        optionalEmployee.get().value = request.value
        employeeRepository.save(optionalEmployee.get())
        return EmployeeResponse(optionalEmployee.get().id,optionalEmployee.get().email,optionalEmployee.get().firstname,optionalEmployee.get().selfValue,optionalEmployee.get().value,optionalEmployee.get().salary, optionalEmployee.get().tasks)
    }

    override fun addTask(request: TaskRequest): EmployeeResponse {
        val employee = employeeRepository.findByEmail(SecurityContextHolder.getContext().authentication.name)
        taskRepository.save(Task(request.description, employee))
        return EmployeeResponse(employee.id,employee.email,employee.firstname,employee.selfValue,employee.value,employee.salary, employee.tasks)
    }

    override fun deleteById(id: Long): String {
        if (!employeeRepository.existsById(id) )
            throw NotFoundException(id.toString())
        employeeRepository.deleteById(id)

        return "employee deleted"
    }

    override fun createEmployee(request: CreateEmployeeRequest): EmployeeResponse {
        val employee = Employee(request.name,request.lastName,request.email, passwordEncoder.encode(request.password))
        employeeRepository.save(employee)
        return EmployeeResponse(employee.id,employee.email,employee.firstname,employee.selfValue,employee.value,employee.salary, employee.tasks)
    }

    override fun updateEmployee(id: Long, request: UpdateEmployeeRequest): EmployeeResponse {
        val optionalEmployee: Optional<Employee> = employeeRepository.findById(id)
        if (optionalEmployee.isEmpty){
            throw ResponseStatusException(HttpStatus.NOT_FOUND, "entity not found")
        }
        val employee = optionalEmployee.get()
        if (request.lastName.isNotEmpty())
            employee.lastname = request.lastName

        if (request.name.isNotEmpty())
            employee.firstname = request.name

        //To-Do
        // Another validations and udpates
        employeeRepository.save(employee)
        return EmployeeResponse(employee.id,employee.email,employee.firstname,employee.selfValue,employee.value,employee.salary, employee.tasks)
    }

}