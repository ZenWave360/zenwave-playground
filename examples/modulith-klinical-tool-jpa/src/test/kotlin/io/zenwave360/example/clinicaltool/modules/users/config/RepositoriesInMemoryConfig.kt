package io.zenwave360.example.clinicaltool.modules.users.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Primary

import io.zenwave360.example.clinicaltool.modules.users.*
import io.zenwave360.example.clinicaltool.modules.users.inmemory.*


//@Configuration
open class RepositoriesInMemoryConfig {


    protected val userRepository = UserRepositoryInMemory()

    @Bean
    @Primary
    fun userRepository(): UserRepositoryInMemory {
        return userRepository
    }

}
