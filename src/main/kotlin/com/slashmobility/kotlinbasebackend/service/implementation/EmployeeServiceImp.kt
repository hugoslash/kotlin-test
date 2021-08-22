package com.slashmobility.kotlinbasebackend.service.implementation

import com.slashmobility.kotlinbasebackend.database.entity.Task
import com.slashmobility.kotlinbasebackend.database.repository.EmployeeRepository
import com.slashmobility.kotlinbasebackend.database.repository.TaskRepository
import com.slashmobility.kotlinbasebackend.dto.request.EmployeeSelfValueRequest
import com.slashmobility.kotlinbasebackend.dto.request.EmployeeValueRequest
import com.slashmobility.kotlinbasebackend.dto.request.TaskRequest
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
    @Autowired
    lateinit var taskRepository : TaskRepository

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
        //var tasks :List<Task>  = mutableListOf(Task(request.description, employee))
        //tasks += employee.tasks!!.toMutableList()
        //employee.tasks = tasks
        return EmployeeResponse(employee.id,employee.email,employee.firstname,employee.selfValue,employee.value,employee.salary, employee.tasks)

    }


}