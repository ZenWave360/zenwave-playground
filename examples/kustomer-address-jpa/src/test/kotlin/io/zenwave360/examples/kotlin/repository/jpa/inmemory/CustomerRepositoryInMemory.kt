package io.zenwave360.examples.kotlin.repository.jpa.inmemory

import io.zenwave360.examples.kotlin.domain.*
import io.zenwave360.examples.kotlin.repository.jpa.CustomerRepository
import java.math.*
import java.time.*
import java.util.*

class CustomerRepositoryInMemory : InMemoryJpaRepository<Customer, Long>(), CustomerRepository {
}
