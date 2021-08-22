package com.slashmobility.kotlinbasebackend.database.repository;

import com.slashmobility.kotlinbasebackend.database.entity.Task
import org.springframework.data.jpa.repository.JpaRepository

interface TaskRepository : JpaRepository<Task, Long> {
}