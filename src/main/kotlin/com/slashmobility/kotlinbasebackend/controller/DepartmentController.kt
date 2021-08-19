package com.slashmobility.kotlinbasebackend.controller

import com.slashmobility.kotlinbasebackend.service.interfaces.DepartmentService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/api/departments")
class DepartmentController {

    @Autowired
    lateinit var departmentService : DepartmentService

    @GetMapping("")
    fun getDepartments(): ResponseEntity<*> {
        return ResponseEntity.ok(departmentService.getDepartments())
    }
}