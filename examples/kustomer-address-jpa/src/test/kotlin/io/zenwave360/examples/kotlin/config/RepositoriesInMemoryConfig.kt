package io.zenwave360.examples.kotlin.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Primary

import io.zenwave360.examples.kotlin.repository.jpa.*
import io.zenwave360.examples.kotlin.repository.jpa.inmemory.*


//@Configuration
open class RepositoriesInMemoryConfig {


    protected val customerRepository = CustomerRepositoryInMemory()

    @Bean
    @Primary
    fun customerRepository(): CustomerRepositoryInMemory {
        return customerRepository
    }

}
