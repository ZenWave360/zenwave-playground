package io.zenwave360.examples.kotlin.web

import io.zenwave360.examples.kotlin.web.*
import io.zenwave360.examples.kotlin.web.dtos.*
import io.zenwave360.examples.kotlin.config.ServicesInMemoryConfig

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.slf4j.Logger
import org.slf4j.LoggerFactory

import java.math.*
import java.time.*
import java.util.*

/**
 * Test controller for CustomerApiController.
 */
class CustomerApiControllerTest {

    private val log: Logger = LoggerFactory.getLogger(javaClass)

    private val context = ServicesInMemoryConfig()

    private val controller = CustomerApiController( context.customerService() )

    @BeforeEach
    fun setUp() {
        context.reloadTestData()
    }


    @Test
    fun createCustomerTest() {
        val reqBody: CustomerDTO = CustomerDTO(name = "", email = "")
        val response = controller.createCustomer(reqBody)
        Assertions.assertEquals(201, response.statusCode.value())
    }

    @Test
    fun getCustomerTest() {
        val customerId: Long = 0
        val response = controller.getCustomer(customerId)
        Assertions.assertEquals(200, response.statusCode.value())
    }

    @Test
    fun updateCustomerTest() {
        val customerId: Long = 0
val reqBody: CustomerDTO = CustomerDTO(name = "", email = "")
        val response = controller.updateCustomer(customerId, reqBody)
        Assertions.assertEquals(200, response.statusCode.value())
    }

    @Test
    fun deleteCustomerTest() {
        val customerId: Long = 0
        val response = controller.deleteCustomer(customerId)
        Assertions.assertEquals(204, response.statusCode.value())
    }

    @Test
    fun searchCustomersTest() {
        val page: Int = 0
val limit: Int = 0
val sort: List<String> = mutableListOf()
val reqBody: CustomerSearchCriteriaDTO = CustomerSearchCriteriaDTO()
        val response = controller.searchCustomers(page, limit, sort, reqBody)
        Assertions.assertEquals(200, response.statusCode.value())
    }


}
