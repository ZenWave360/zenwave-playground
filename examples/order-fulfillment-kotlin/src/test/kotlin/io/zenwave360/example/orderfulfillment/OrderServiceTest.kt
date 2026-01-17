package io.zenwave360.example.orderfulfillment

import io.zenwave360.example.orderfulfillment.*
import io.zenwave360.example.orderfulfillment.config.*
import io.zenwave360.example.orderfulfillment.domain.*
import io.zenwave360.example.orderfulfillment.dtos.*
import io.zenwave360.example.orderfulfillment.inmemory.*
import io.zenwave360.example.orderfulfillment.mappers.*
import java.time.*
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.fail
import org.mockito.Mockito.*
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.math.BigDecimal

/** Acceptance Test for OrderService. */
class OrderServiceTest {

    private val log: Logger = LoggerFactory.getLogger(javaClass)

    val context = ServicesInMemoryConfig()
    val orderService: OrderService = context.orderService()

    val orderRepository: OrderRepositoryInMemory = context.orderRepository()

    @BeforeEach
    fun setUp() {
        context.reloadTestData()
    }

    @Test
    fun placeOrderTest() {
        val input = PlaceOrderInput(
            items = mutableListOf(
                OrderItem(
                    productId = "PROD-001",
                    productName = "Wireless Bluetooth Headphones",
                    quantity = 1 as Integer?,
                    unitPrice = BigDecimal.valueOf(199.99)
                )
            ),
            currency = "USD"
        )
        val order = orderService.placeOrder(input)
        assertNotNull(order)
        assertNotNull(order.orderNumber)
        assertNotNull(order.id)
    }

    @Test
    fun payOrderTest() {
        val order = orderService.payOrder("ORD-2024-001", PayOrderInput("PAY-REF-12345"))
        assertNotNull(order)
        assertNotNull(order.paymentReference)
        assertEquals(OrderStatus.PAID, order.status)
    }

    @Test
    fun shipOrderTest() {
        val order = orderService.shipOrder("ORD-2024-003", ShipOrderInput("TRK-67890"))
        assertNotNull(order)
        assertNotNull(order.trackingNumber)
        assertEquals(OrderStatus.SHIPPED, order.status)
    }

    @Test
    fun cancelOrderTest() {
        val order = orderService.cancelOrder("ORD-2024-001")
        assertNotNull(order)
        assertEquals(OrderStatus.CANCELLED, order.status)
    }

    @Test
    fun cancelShippedOrderTest() {
        try {
            val order = orderService.cancelOrder("ORD-2024-003")
            fail("Expected IllegalStateException")
        } catch (e: IllegalStateException) {
            assertEquals("Shipped orders can not be cancelled", e.message)
        }
    }

    @Test
    fun getOrderTest() {
        val orderNumber = "ORD-2024-001"
        val order = orderService.getOrder(orderNumber)
        assertNotNull(order)
        assertEquals(OrderStatus.PLACED, order?.status)
    }
}
