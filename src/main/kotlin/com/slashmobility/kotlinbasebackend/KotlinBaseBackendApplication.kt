package com.slashmobility.kotlinbasebackend

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaAuditing

@SpringBootApplication
class KotlinBaseBackendApplication

fun main(args: Array<String>) {
	runApplication<KotlinBaseBackendApplication>(*args)
}
