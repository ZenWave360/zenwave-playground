package io.zenwave360.examples.kotlin.config

import org.springframework.boot.persistence.autoconfigure.EntityScan
import org.springframework.data.jpa.repository.config.EnableJpaAuditing
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.transaction.annotation.EnableTransactionManagement

@org.springframework.context.annotation.Configuration
@EnableJpaRepositories("io.zenwave360.examples.kotlin")
@EntityScan("io.zenwave360.examples.kotlin")
@EnableJpaAuditing(auditorAwareRef = "springSecurityAuditorAware")
@EnableTransactionManagement
open class DatabaseConfiguration
