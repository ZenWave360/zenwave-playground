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
 * Integration tests for the [CustomerApi] REST controller.
 */
open class CustomerApiIntegrationTest : BaseWebTestClientTest() {



        /**
        * Test: createCustomer for OK.
        */
        @Test
        fun testCreateCustomer_201() {
        val requestBody = """
            {
              "id" : 63,
              "version" : 10,
              "name" : "name-zmbfib58c16uzmn4q2dmg",
              "email" : "jesse.barrows@yahoo.com",
              "addresses" : [ {
                "street" : "street-msjedgjpv400vu89dqs",
                "city" : "Melodeeburgh"
              } ],
              "paymentMethods" : [ {
                "id" : 24,
                "version" : 34,
                "type" : "VISA",
                "cardNumber" : "cardNumber-a9u687hxv4"
              } ]
            }
        """

        webTestClient.method(POST).uri("/api/customers")
        .accept(MediaType.APPLICATION_JSON)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(requestBody)
        .exchange()
        .expectStatus().isEqualTo(201)
            .expectHeader().contentType(MediaType.APPLICATION_JSON)
            .expectBody()
            .jsonPath("$.id").isNotEmpty()
            .jsonPath("$.version").isNotEmpty()
            .jsonPath("$.name").isNotEmpty()
            .jsonPath("$.email").isNotEmpty()
            .jsonPath("$.addresses").isNotEmpty()
                .jsonPath("$.addresses").isArray()
                    .jsonPath("$.addresses[0].street").isNotEmpty()
                    .jsonPath("$.addresses[0].city").isNotEmpty()
            .jsonPath("$.paymentMethods").isNotEmpty()
                .jsonPath("$.paymentMethods").isArray()
                    .jsonPath("$.paymentMethods[0].id").isNotEmpty()
                    .jsonPath("$.paymentMethods[0].version").isNotEmpty()
                    .jsonPath("$.paymentMethods[0].type").isNotEmpty()
                    .jsonPath("$.paymentMethods[0].cardNumber").isNotEmpty()
        }

        /**
        * Test: getCustomer for OK.
        */
        @Test
        fun testGetCustomer_200() {
        val customerId = ""

        webTestClient.method(GET).uri("/api/customers/{customerId}", customerId)
        .accept(MediaType.APPLICATION_JSON)
        .exchange()
        .expectStatus().isEqualTo(200)
            .expectHeader().contentType(MediaType.APPLICATION_JSON)
            .expectBody()
            .jsonPath("$.id").isNotEmpty()
            .jsonPath("$.version").isNotEmpty()
            .jsonPath("$.name").isNotEmpty()
            .jsonPath("$.email").isNotEmpty()
            .jsonPath("$.addresses").isNotEmpty()
                .jsonPath("$.addresses").isArray()
                    .jsonPath("$.addresses[0].street").isNotEmpty()
                    .jsonPath("$.addresses[0].city").isNotEmpty()
            .jsonPath("$.paymentMethods").isNotEmpty()
                .jsonPath("$.paymentMethods").isArray()
                    .jsonPath("$.paymentMethods[0].id").isNotEmpty()
                    .jsonPath("$.paymentMethods[0].version").isNotEmpty()
                    .jsonPath("$.paymentMethods[0].type").isNotEmpty()
                    .jsonPath("$.paymentMethods[0].cardNumber").isNotEmpty()
        }

        /**
        * Test: updateCustomer for OK.
        */
        @Test
        fun testUpdateCustomer_200() {
        val requestBody = """
            {
              "id" : 68,
              "version" : 89,
              "name" : "name-91rbx8",
              "email" : "miki.baumbach@hotmail.com",
              "addresses" : [ {
                "street" : "street-6aw8fon78i",
                "city" : "New Erikaland"
              } ],
              "paymentMethods" : [ {
                "id" : 15,
                "version" : 53,
                "type" : "MASTERCARD",
                "cardNumber" : "cardNumber-nxo9a9bt29u3ik7"
              } ]
            }
        """
        val customerId = ""

        webTestClient.method(PUT).uri("/api/customers/{customerId}", customerId)
        .accept(MediaType.APPLICATION_JSON)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(requestBody)
        .exchange()
        .expectStatus().isEqualTo(200)
            .expectHeader().contentType(MediaType.APPLICATION_JSON)
            .expectBody()
            .jsonPath("$.id").isNotEmpty()
            .jsonPath("$.version").isNotEmpty()
            .jsonPath("$.name").isNotEmpty()
            .jsonPath("$.email").isNotEmpty()
            .jsonPath("$.addresses").isNotEmpty()
                .jsonPath("$.addresses").isArray()
                    .jsonPath("$.addresses[0].street").isNotEmpty()
                    .jsonPath("$.addresses[0].city").isNotEmpty()
            .jsonPath("$.paymentMethods").isNotEmpty()
                .jsonPath("$.paymentMethods").isArray()
                    .jsonPath("$.paymentMethods[0].id").isNotEmpty()
                    .jsonPath("$.paymentMethods[0].version").isNotEmpty()
                    .jsonPath("$.paymentMethods[0].type").isNotEmpty()
                    .jsonPath("$.paymentMethods[0].cardNumber").isNotEmpty()
        }

        /**
        * Test: deleteCustomer for OK.
        */
        @Test
        fun testDeleteCustomer_204() {
        val customerId = ""

        webTestClient.method(DELETE).uri("/api/customers/{customerId}", customerId)
        .accept(MediaType.APPLICATION_JSON)
        .exchange()
        .expectStatus().isEqualTo(204)
        }

        /**
        * Test: searchCustomers for OK.
        */
        @Test
        fun testSearchCustomers_200() {
        val requestBody = """
            {
              "name" : "name-ynsjnot32jxop55ze1pm7",
              "email" : "rigoberto.walker@yahoo.com",
              "city" : "Port Takishafurt",
              "state" : "state-alyno2tdpxbb1hyflo89"
            }
        """
        val page = ""
        val limit = ""
        val sort = ""

        webTestClient.method(POST).uri({ it.path("/api/customers/search").queryParam("page", page).queryParam("limit", limit).queryParam("sort", sort).build(page, limit, sort) })
        .accept(MediaType.APPLICATION_JSON)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(requestBody)
        .exchange()
        .expectStatus().isEqualTo(200)
            .expectHeader().contentType(MediaType.APPLICATION_JSON)
            .expectBody()
            .jsonPath("$.number").isNotEmpty()
            .jsonPath("$.numberOfElements").isNotEmpty()
            .jsonPath("$.size").isNotEmpty()
            .jsonPath("$.totalElements").isNotEmpty()
            .jsonPath("$.totalPages").isNotEmpty()
            .jsonPath("$.content").isNotEmpty()
                .jsonPath("$.content").isArray()
                    .jsonPath("$.content[0].id").isNotEmpty()
                    .jsonPath("$.content[0].version").isNotEmpty()
                    .jsonPath("$.content[0].name").isNotEmpty()
                    .jsonPath("$.content[0].email").isNotEmpty()
                    .jsonPath("$.content[0].addresses").isNotEmpty()
                    .jsonPath("$.content[0].paymentMethods").isNotEmpty()
        }

}
