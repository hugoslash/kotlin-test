package com.slashmobility.kotlinbasebackend.database.repository;

import com.slashmobility.kotlinbasebackend.config.AuthorityName
import com.slashmobility.kotlinbasebackend.database.entity.Authority
import org.springframework.data.jpa.repository.JpaRepository

interface AuthorityRepository : JpaRepository<Authority, Long> {
    fun findByName(input: AuthorityName): Authority
}