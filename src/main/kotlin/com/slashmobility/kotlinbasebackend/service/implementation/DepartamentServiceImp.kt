package com.slashmobility.kotlinbasebackend.service.implementation

import com.slashmobility.kotlinbasebackend.database.entity.Departament
import com.slashmobility.kotlinbasebackend.database.repository.DepartamentRepository
import com.slashmobility.kotlinbasebackend.service.interfaces.DepartamentService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class DepartamentServiceImp  : DepartamentService {


    @Autowired
    lateinit var departamentRepository: DepartamentRepository

    override fun getDepartaments(): List<Departament> = departamentRepository.findAll()
}