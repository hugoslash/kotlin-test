package com.slashmobility.kotlinbasebackend.database.repository;

import com.slashmobility.kotlinbasebackend.database.entity.Departament
import org.springframework.data.jpa.repository.JpaRepository

interface DepartamentRepository : JpaRepository<Departament, Long> {
}