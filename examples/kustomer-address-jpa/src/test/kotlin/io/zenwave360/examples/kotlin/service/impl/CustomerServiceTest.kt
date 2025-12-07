package io.zenwave360.examples.kotlin.service.impl

import io.zenwave360.examples.kotlin.config.*
import io.zenwave360.examples.kotlin.domain.*
import io.zenwave360.examples.kotlin.service.*
import io.zenwave360.examples.kotlin.service.dtos.*
import io.zenwave360.examples.kotlin.service.impl.mappers.*
import io.zenwave360.examples.kotlin.repository.jpa.*
import io.zenwave360.examples.kotlin.repository.jpa.inmemory.*

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mapstruct.factory.Mappers
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.PageRequest

import java.util.Map
import java.util.Optional
import java.time.*
import java.math.BigDecimal

import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Assertions.assertFalse
import org.mockito.Mockito.*

/**
 * Acceptance Test for CustomerService.
 */
class  CustomerServiceTest {

    private val log: Logger = LoggerFactory.getLogger(javaClass)

    val context = ServicesInMemoryConfig()
    val customerService: CustomerService = context.customerService()

    val customerRepository: CustomerRepositoryInMemory = context.customerRepository()


    @BeforeEach
    fun setUp() {
        context.reloadTestData()
    }


    @Test
    fun createCustomerTest() {
        val input = Customer()
        input.name = "name"
        input.email = "me@email.com"
        input.addresses = mutableListOf(Address().apply {
            street = "street"
            city = "city"
        })
        val customer = customerService.createCustomer(input)
        assertNotNull(customer.id)
        assertTrue(customerRepository.containsEntity(customer))
}

    @Test
    fun getCustomerTest() {
        val id: Long = 1L
        val customer = customerService.getCustomer(id)
        assertTrue(customer.isPresent)
}

    @Test
    fun updateCustomerTest() {
        val id: Long = 1L
        val input = Customer()
        input.name = "name"
        input.email = "me@email.com"
        input.addresses = mutableListOf(Address().apply {
            street = "street"
            city = "city"
        })
        assertTrue(customerRepository.containsKey(id))
        val customer = customerService.updateCustomer(id, input)
        assertTrue(customer.isPresent)
        assertTrue(customerRepository.containsEntity(customer.get()))
}

    @Test
    fun deleteCustomerTest() {
        val id: Long = 1L
        assertTrue(customerRepository.containsKey(id))
        customerService.deleteCustomer(id)
        assertFalse(customerRepository.containsKey(id))
}

    @Test
    fun searchCustomersTest() {
        val searchCriteria = CustomerSearchCriteria()
        val results = customerService.searchCustomers(searchCriteria, PageRequest.of(0, 10))
        assertNotNull(results)
        assertFalse(results.isEmpty)
}

}
