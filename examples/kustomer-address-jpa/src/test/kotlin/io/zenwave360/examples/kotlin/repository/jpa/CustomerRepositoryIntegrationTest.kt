package io.zenwave360.examples.kotlin.repository.jpa

import io.zenwave360.examples.kotlin.repository.jpa.BaseRepositoryIntegrationTest
import io.zenwave360.examples.kotlin.domain.*
import io.zenwave360.examples.kotlin.repository.jpa.CustomerRepository

import java.util.HashSet
import java.util.HashMap
import java.util.List
import java.time.*
import java.math.BigDecimal

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired

import jakarta.persistence.EntityManager

class CustomerRepositoryIntegrationTest : BaseRepositoryIntegrationTest() {

    @Autowired
    lateinit var entityManager: EntityManager

    @Autowired
    lateinit var customerRepository: CustomerRepository

    @Test
    fun findAllTest() {
        val results = customerRepository.findAll()
        Assertions.assertFalse(results.isEmpty())
    }

    @Test
    fun findByIdTest() {
        val id = 1L
        val customer = customerRepository.findById(id).orElseThrow()
        Assertions.assertNotNull(customer.id)
        Assertions.assertNotNull(customer.version)
        Assertions.assertNotNull(customer.createdBy)
        Assertions.assertNotNull(customer.createdDate)
    }

    @Test
    fun saveTest() {
        val customer = Customer()
        customer.name = "Jane Smith"
        customer.email = "jane.smith@example.com"
        customer.addresses = mutableListOf(
            Address().apply {
                street = "456 Elm St"
                city = "Othertown"
            }
        )

        // OneToMany paymentMethods owner: true
        val paymentMethods = PaymentMethod()
        paymentMethods.type = PaymentMethodType.VISA
        paymentMethods.cardNumber = "6543210987654321"
        customer.paymentMethods = HashSet()
        customer.addPaymentMethods(paymentMethods)

        // Persist aggregate root
        val created = customerRepository.save(customer)

        // reloading to get relationships persisted by id
        entityManager.flush()
        entityManager.refresh(created)
        Assertions.assertNotNull(created.id)
        Assertions.assertNotNull(created.version)
        Assertions.assertNotNull(created.createdBy)
        Assertions.assertNotNull(created.createdDate)

        Assertions.assertTrue(customer.paymentMethods?.stream()?.allMatch { item -> item.id != null } == true)
    }

    @Test
    fun updateTest() {
        val id = 1L
        val customer = customerRepository.findById(id).orElseThrow()
        customer.name = "updated"
        customer.email = "updated@email.com"

        val updated = customerRepository.save(customer)
        Assertions.assertEquals("updated", updated.name)
        Assertions.assertEquals("updated@email.com", updated.email)
    }

    @Test
    fun deleteTest() {
        val id = 1L
        customerRepository.deleteById(id)
        val notFound = customerRepository.findById(id)
        Assertions.assertFalse(notFound.isPresent)
    }
}
