package io.zenwave360.examples.kotlin.web

import io.zenwave360.examples.kotlin.web.*
import io.zenwave360.examples.kotlin.web.dtos.*
import io.zenwave360.examples.kotlin.web.BaseWebTestClientTest

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.test.web.reactive.server.WebTestClient

import java.math.BigDecimal

import org.springframework.http.HttpMethod.*

/**
 * Business Flow Test for: createCustomer, getCustomer, updateCustomer, deleteCustomer.
 */
open class CreateUpdateDeleteCustomerIntegrationTest : BaseWebTestClientTest() {

    /**
     * Business Flow Test for: createCustomer, getCustomer, updateCustomer, deleteCustomer.
     */
    @Test
    fun testCreateUpdateDeleteCustomerIntegrationTest() {
        // createCustomer: createCustomer
        val customerRequestBody0 = """
            {
              "email": "jane.doe@example.com",
              "name": "Jane Doe",
              "addresses": [
                {
                  "city": "Othertown",
                  "street": "456 Elm St"
                }
              ],
              "paymentMethods": [
                {
                  "type": "VISA",
                  "cardNumber": "6543210987654321"
                }
              ]
            }
        """

        val createCustomerResponse0 = webTestClient.method(POST).uri("/api/customers")
            .accept(MediaType.APPLICATION_JSON)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(customerRequestBody0)
            .exchange()
            .expectStatus().isEqualTo(201)
            .expectHeader().contentType(MediaType.APPLICATION_JSON)
            .returnResult(CustomerDTO::class.java)

        // getCustomer: getCustomer
        val customerId = createCustomerResponse0.responseBody.blockFirst()!!.id!!

        val getCustomerResponse1 = webTestClient.method(GET).uri("/api/customers/{customerId}", customerId)
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus().isEqualTo(200)
            .expectHeader().contentType(MediaType.APPLICATION_JSON)
            .returnResult(CustomerDTO::class.java)

        // updateCustomer: updateCustomer
        val existingCustomer = getCustomerResponse1.responseBody.blockFirst()!!
        val customerRequestBody2 = existingCustomer.copy(
            name = "updated",
            email = "updated@email.com",
            paymentMethods = existingCustomer.paymentMethods?.map {
                it.copy(type = PaymentMethodTypeDTO.VISA)
            }
        )

        val updateCustomerResponse2 = webTestClient.method(PUT).uri("/api/customers/{customerId}", customerId)
            .accept(MediaType.APPLICATION_JSON)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(customerRequestBody2)
            .exchange()
            .expectStatus().isEqualTo(200)
            .expectHeader().contentType(MediaType.APPLICATION_JSON)
            .returnResult(CustomerDTO::class.java)

        // deleteCustomer: deleteCustomer
        webTestClient.method(DELETE).uri("/api/customers/{customerId}", customerId)
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus().isEqualTo(204)

        // getCustomer: getCustomer (not found)
        webTestClient.method(GET).uri("/api/customers/{customerId}", customerId)
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus().isEqualTo(404)
    }

}
