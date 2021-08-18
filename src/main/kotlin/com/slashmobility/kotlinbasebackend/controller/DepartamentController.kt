package com.slashmobility.kotlinbasebackend.controller

import com.slashmobility.kotlinbasebackend.service.interfaces.DepartamentService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/api/departaments")
class DepartamentController {

    @Autowired
    lateinit var departamentService : DepartamentService

    @GetMapping("")
    fun getDepartaments(): ResponseEntity<*> {
        return ResponseEntity.ok(departamentService.getDepartaments())
    }
}