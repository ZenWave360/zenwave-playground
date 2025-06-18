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
              "id" : 41,
              "version" : 75,
              "name" : "name-cri0m8h4v96piit1m",
              "email" : "anabel.dietrich@yahoo.com",
              "addresses" : [ {
                "street" : "street-5ij3u4dkzk0s4ndbu",
                "city" : "North Renaldoshire"
              } ],
              "paymentMethods" : [ {
                "id" : 11,
                "version" : 93,
                "type" : "MASTERCARD",
                "cardNumber" : "cardNumber-wu58mfimeift0p0"
              } ]
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
        val customerId1 = ""

        val getCustomerResponse1 = webTestClient.method(GET).uri("/api/customers/{customerId}", customerId1)
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus().isEqualTo(200)
            .expectHeader().contentType(MediaType.APPLICATION_JSON)
            .returnResult(CustomerDTO::class.java)
        // updateCustomer: updateCustomer
        val customerRequestBody2 = """
            {
              "id" : 56,
              "version" : 90,
              "name" : "name-w8p45",
              "email" : "domenic.hudson@yahoo.com",
              "addresses" : [ {
                "street" : "street-lfgox0iaa262os31m2b",
                "city" : "West Denicemouth"
              } ],
              "paymentMethods" : [ {
                "id" : 38,
                "version" : 77,
                "type" : "MASTERCARD",
                "cardNumber" : "cardNumber-q98zhan60nvl7ig"
              } ]
            }
        """
        val customerId2 = ""

        val updateCustomerResponse2 = webTestClient.method(PUT).uri("/api/customers/{customerId}", customerId2)
            .accept(MediaType.APPLICATION_JSON)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(customerRequestBody2)
            .exchange()
            .expectStatus().isEqualTo(200)
            .expectHeader().contentType(MediaType.APPLICATION_JSON)
            .returnResult(CustomerDTO::class.java)
        // deleteCustomer: deleteCustomer
        val customerId3 = ""

        webTestClient.method(DELETE).uri("/api/customers/{customerId}", customerId3)
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus().isEqualTo(204)
    }

}
