package com.slashmobility.kotlinbasebackend.controller

import com.slashmobility.kotlinbasebackend.constants.ROLE_MANAGER
import com.slashmobility.kotlinbasebackend.dto.request.CreateEmployeeRequest
import com.slashmobility.kotlinbasebackend.dto.request.UpdateEmployeeRequest
import com.slashmobility.kotlinbasebackend.dto.response.EmployeeResponse
import com.slashmobility.kotlinbasebackend.service.interfaces.EmployeeService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/employee/")
class EmployeeCrudController {

    @Autowired
    lateinit var employeeService: EmployeeService

    @PostMapping("")
    @PreAuthorize("hasRole('$ROLE_MANAGER')")
    fun createEmployee (@RequestBody request: CreateEmployeeRequest): ResponseEntity<EmployeeResponse> = ResponseEntity.ok(employeeService.createEmployee(request))

    @PatchMapping("{id}")
    @PreAuthorize("hasRole('$ROLE_MANAGER')")
    fun updateEmployee (@PathVariable id:Long, @RequestBody request: UpdateEmployeeRequest): ResponseEntity<EmployeeResponse> = ResponseEntity.ok(employeeService.updateEmployee(id, request))

    @DeleteMapping("{id}")
    @PreAuthorize("hasRole('$ROLE_MANAGER')")
    fun deleteEmployee (@PathVariable id:Long): ResponseEntity<String> = ResponseEntity.ok(employeeService.deleteById(id))

}