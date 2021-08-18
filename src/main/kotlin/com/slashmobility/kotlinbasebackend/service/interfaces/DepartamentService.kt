package com.slashmobility.kotlinbasebackend.service.interfaces

import com.slashmobility.kotlinbasebackend.database.entity.Departament
import com.slashmobility.kotlinbasebackend.database.repository.DepartamentRepository
import com.slashmobility.kotlinbasebackend.dto.request.LoginRequest
import com.slashmobility.kotlinbasebackend.dto.response.LoginResponse
import org.springframework.beans.factory.annotation.Autowired

interface DepartamentService {
    fun getDepartaments() : List<Departament> ;
}