package io.zenwave360.example.orderfulfillment

import io.zenwave360.example.orderfulfillment.domain.*
import jakarta.persistence.EntityManager
import java.math.BigDecimal
import java.time.*
import java.util.List
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.findByIdOrNull

class OrderRepositoryIntegrationTest : BaseRepositoryIntegrationTest() {

    @Autowired lateinit var entityManager: EntityManager

    @Autowired lateinit var orderRepository: OrderRepository

    @Test
    fun findAllTest() {
        val results = orderRepository.findAll()
        Assertions.assertFalse(results.isEmpty())
    }

    @Test
    fun findByIdTest() {
        val id = 1L
        val order = orderRepository.findByIdOrNull(id) ?: throw NoSuchElementException(" not found with id: $id")
        Assertions.assertNotNull(order.id)
        Assertions.assertNotNull(order.version)
        Assertions.assertNotNull(order.createdBy)
        Assertions.assertNotNull(order.createdDate)
    }

    @Test
    fun saveTest() {
        val order = Order()
        order.orderNumber = "ORD-TEST-001"
        order.status = OrderStatus.PLACED
        order.totalAmount = BigDecimal.valueOf(199.99)
        order.currency = "USD"
        order.paymentReference = "PAY-TEST-001"
        order.trackingNumber = "TRK-TEST-001"
        order.items = mutableListOf(
            OrderItem().apply {
                productId = "PROD-TEST-001"
                productName = "Test Product"
                quantity = 2 as Integer?
                unitPrice = BigDecimal.valueOf(99.99)
            }
        )

        // Persist aggregate root
        val created = orderRepository.save(order)

        // reloading to get relationships persisted by id
        entityManager.flush()
        entityManager.refresh(created)
        Assertions.assertNotNull(created.id)
        Assertions.assertNotNull(created.version)
        Assertions.assertNotNull(created.createdBy)
        Assertions.assertNotNull(created.createdDate)
    }

    @Test
    fun updateTest() {
        val id = 1L
        val order = orderRepository.findByIdOrNull(id) ?: throw NoSuchElementException(" not found with id: $id")
        order.orderNumber = "ORD-UPDATED-001"
        order.status = OrderStatus.PAID
        order.totalAmount = BigDecimal.valueOf(299.99)
        order.currency = "EUR"
        order.paymentReference = "PAY-UPDATED-001"
        order.trackingNumber = "TRK-UPDATED-001"
        order.items = mutableListOf(
            OrderItem().apply {
                productId = "PROD-UPDATED-001"
                productName = "Updated Product"
                quantity = 1 as Integer?
                unitPrice = BigDecimal.valueOf(299.99)
            }
        )

        val updated = orderRepository.save(order)
        Assertions.assertEquals("ORD-UPDATED-001", updated.orderNumber)
        Assertions.assertEquals(OrderStatus.PAID, updated.status)
        Assertions.assertEquals(BigDecimal.valueOf(299.99), updated.totalAmount)
        Assertions.assertEquals("EUR", updated.currency)
        Assertions.assertEquals("PAY-UPDATED-001", updated.paymentReference)
        Assertions.assertEquals("TRK-UPDATED-001", updated.trackingNumber)
        Assertions.assertEquals(1, updated.items.size)
    }

    @Test
    fun deleteTest() {
        val id = 1L
        orderRepository.deleteById(id)
        val notFound = orderRepository.findByIdOrNull(id)
        Assertions.assertNull(notFound)
    }
}
