package io.zenwave360.example.clinicaltool.modules.users.config

import io.zenwave360.example.clinicaltool.modules.users.*
import io.zenwave360.example.clinicaltool.modules.users.inmemory.*
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Primary

// @Configuration
open class RepositoriesInMemoryConfig {

    protected val userRepository = UserRepositoryInMemory()

    @Bean
    @Primary
    fun userRepository(): UserRepositoryInMemory {
        return userRepository
    }
}
