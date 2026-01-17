package io.zenwave360.example.orderfulfillment

import io.zenwave360.example.orderfulfillment.web.*
import io.zenwave360.example.orderfulfillment.web.model.*
import io.zenwave360.example.orderfulfillment.config.ServicesInMemoryConfig

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.slf4j.Logger
import org.slf4j.LoggerFactory

import java.math.*
import java.time.*
import java.util.*

/**
 * Test controller for OrderApiController.
 */
class OrderApiControllerTest {

    private val log: Logger = LoggerFactory.getLogger(javaClass)

    private val context = ServicesInMemoryConfig()

    private val controller = OrderApiController(context.orderService())

    @BeforeEach
    fun setUp() {
        context.reloadTestData()
    }


    @Test
    fun placeOrderTest() {
        val orderItem = OrderItemDTO(productId = "PROD-001", productName = "Wireless Bluetooth Headphones", quantity = 1, unitPrice = BigDecimal.valueOf(199.99))
        val reqBody: PlaceOrderInputDTO = PlaceOrderInputDTO(currency = "aaa", items = mutableListOf(orderItem))
        val response = controller.placeOrder(reqBody)
        Assertions.assertEquals(201, response.statusCode.value())
    }

    @Test
    fun payOrderTest() {
        val orderNumber: String = "ORD-2024-001"
        val reqBody: PayOrderInputDTO = PayOrderInputDTO(paymentReference = "PAY-REF-12345")
        val response = controller.payOrder(orderNumber, reqBody)
        Assertions.assertEquals(201, response.statusCode.value())
    }

    @Test
    fun shipOrderTest() {
        val orderNumber: String = "ORD-2024-003"
        val reqBody: ShipOrderInputDTO = ShipOrderInputDTO(trackingNumber = "TRK-67890")
        val response = controller.shipOrder(orderNumber, reqBody)
        Assertions.assertEquals(201, response.statusCode.value())
    }

    @Test
    fun cancelOrderTest() {
        val orderNumber: String = "ORD-2024-001"
        val response = controller.cancelOrder(orderNumber)
        Assertions.assertEquals(201, response.statusCode.value())
    }

    @Test
    fun getOrderTest() {
        val orderNumber: String = "ORD-2024-001"
        val response = controller.getOrder(orderNumber)
        Assertions.assertEquals(200, response.statusCode.value())
    }


}
