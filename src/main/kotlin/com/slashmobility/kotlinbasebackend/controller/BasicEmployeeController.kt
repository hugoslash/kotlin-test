package com.slashmobility.kotlinbasebackend.controller

import com.slashmobility.kotlinbasebackend.constants.ROLE_GENERAL
import com.slashmobility.kotlinbasebackend.constants.ROLE_MANAGER
import com.slashmobility.kotlinbasebackend.dto.request.EmployeeSelfValueRequest
import com.slashmobility.kotlinbasebackend.dto.request.EmployeeValueRequest
import com.slashmobility.kotlinbasebackend.dto.request.TaskRequest
import com.slashmobility.kotlinbasebackend.dto.response.EmployeeResponse
import com.slashmobility.kotlinbasebackend.service.interfaces.EmployeeService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/employee/")
class BasicEmployeeController {

    @Autowired
    lateinit var employeeService: EmployeeService

    @GetMapping("")
    @PreAuthorize("hasRole('$ROLE_GENERAL')")
    fun getEmployees (@RequestBody request: EmployeeSelfValueRequest): ResponseEntity<EmployeeResponse> = ResponseEntity.ok(employeeService.selfValue(request))

    @PostMapping("self-value")
    @PreAuthorize("hasRole('$ROLE_GENERAL')")
    fun selfvalue (@RequestBody request: EmployeeSelfValueRequest): ResponseEntity<EmployeeResponse> = ResponseEntity.ok(employeeService.selfValue(request))

    @PostMapping("value")
    @PreAuthorize("hasRole('$ROLE_MANAGER')")
    fun value (@RequestBody request: EmployeeValueRequest): ResponseEntity<EmployeeResponse> = ResponseEntity.ok(employeeService.value(request))

    @PostMapping("task")
    @PreAuthorize("hasRole('$ROLE_GENERAL')")
    fun addTask (@RequestBody request: TaskRequest): ResponseEntity<EmployeeResponse> = ResponseEntity.ok(employeeService.addTask(request))


}