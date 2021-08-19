package com.slashmobility.kotlinbasebackend.config

import org.springframework.context.annotation.Bean
import org.springframework.data.domain.AuditorAware
import org.springframework.data.jpa.repository.config.EnableJpaAuditing
import org.springframework.security.core.context.SecurityContextHolder
import java.util.*

@EnableJpaAuditing(auditorAwareRef = "auditorProvider")
class JpaAuditingConfiguration {
    @Bean
    fun auditorProvider(): AuditorAware<String> {
        return if (SecurityContextHolder.getContext().authentication == null) AuditorAware {
            Optional.ofNullable(
                "example"
            )
        } else AuditorAware {
            Optional.ofNullable(
                SecurityContextHolder.getContext().authentication.name
            )
        }
    }
}