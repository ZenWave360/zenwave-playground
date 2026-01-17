package io.zenwave360.example.orderfulfillment.config

import io.zenwave360.example.orderfulfillment.*
import io.zenwave360.example.orderfulfillment.inmemory.*
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Primary

// @Configuration
open class RepositoriesInMemoryConfig {

    protected val orderRepository = OrderRepositoryInMemory()

    @Bean
    @Primary
    fun orderRepository(): OrderRepositoryInMemory {
        return orderRepository
    }
}
