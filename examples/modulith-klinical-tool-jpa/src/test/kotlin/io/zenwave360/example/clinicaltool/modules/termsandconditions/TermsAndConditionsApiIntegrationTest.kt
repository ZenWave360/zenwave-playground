package io.zenwave360.example.clinicaltool.modules.termsandconditions

import io.zenwave360.example.clinicaltool.modules.termsandconditions.*
import io.zenwave360.example.clinicaltool.modules.termsandconditions.dtos.*
import io.zenwave360.example.clinicaltool.common.BaseWebTestClientTest

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.test.web.reactive.server.WebTestClient

import java.math.BigDecimal

import org.springframework.http.HttpMethod.*

/**
 * Integration tests for the [TermsAndConditionsApi] REST controller.
 */
open class TermsAndConditionsApiIntegrationTest : BaseWebTestClientTest() {



        /**
        * Test: listTermsAndConditions for OK.
        */
        @Test
        fun testListTermsAndConditions_200() {

        webTestClient.method(GET).uri("/api/terms-and-conditions")
        .accept(MediaType.APPLICATION_JSON)
        .exchange()
        .expectStatus().isEqualTo(200)
            .expectHeader().contentType(MediaType.APPLICATION_JSON)
            .expectBody()
        }

        /**
        * Test: createTermsAndConditions for OK.
        */
        @Test
        fun testCreateTermsAndConditions_201() {
        val requestBody = """
            {
              "id" : 73,
              "version" : 53,
              "content" : "content-aqty",
              "lang" : "lang-5msj6k6kiy0ag9h9rw0n5",
              "contentVersion" : "contentVersion-1z",
              "startDate" : "2025-12-08"
            }
        """

        webTestClient.method(POST).uri("/api/terms-and-conditions")
        .accept(MediaType.APPLICATION_JSON)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(requestBody)
        .exchange()
        .expectStatus().isEqualTo(201)
            .expectHeader().contentType(MediaType.APPLICATION_JSON)
            .expectBody()
            .jsonPath("$.id").isNotEmpty()
            .jsonPath("$.version").isNotEmpty()
            .jsonPath("$.content").isNotEmpty()
            .jsonPath("$.lang").isNotEmpty()
            .jsonPath("$.contentVersion").isNotEmpty()
            .jsonPath("$.startDate").isNotEmpty()
        }

        /**
        * Test: getTermsAndConditions for OK.
        */
        @Test
        fun testGetTermsAndConditions_200() {
        val id = ""

        webTestClient.method(GET).uri("/api/terms-and-conditions/{id}", id)
        .accept(MediaType.APPLICATION_JSON)
        .exchange()
        .expectStatus().isEqualTo(200)
            .expectHeader().contentType(MediaType.APPLICATION_JSON)
            .expectBody()
            .jsonPath("$.id").isNotEmpty()
            .jsonPath("$.version").isNotEmpty()
            .jsonPath("$.content").isNotEmpty()
            .jsonPath("$.lang").isNotEmpty()
            .jsonPath("$.contentVersion").isNotEmpty()
            .jsonPath("$.startDate").isNotEmpty()
        }

        /**
        * Test: updateTermsAndConditions for OK.
        */
        @Test
        fun testUpdateTermsAndConditions_200() {
        val requestBody = """
            {
              "id" : 23,
              "version" : 36,
              "content" : "content-f126f6cs37waupgnno",
              "lang" : "lang-c5ul76unky68fqrrg0",
              "contentVersion" : "contentVersion-crggms3i5r6",
              "startDate" : "2025-12-08"
            }
        """
        val id = ""

        webTestClient.method(PUT).uri("/api/terms-and-conditions/{id}", id)
        .accept(MediaType.APPLICATION_JSON)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(requestBody)
        .exchange()
        .expectStatus().isEqualTo(200)
            .expectHeader().contentType(MediaType.APPLICATION_JSON)
            .expectBody()
            .jsonPath("$.id").isNotEmpty()
            .jsonPath("$.version").isNotEmpty()
            .jsonPath("$.content").isNotEmpty()
            .jsonPath("$.lang").isNotEmpty()
            .jsonPath("$.contentVersion").isNotEmpty()
            .jsonPath("$.startDate").isNotEmpty()
        }

        /**
        * Test: getCurrentTermsAndConditions for OK.
        */
        @Test
        fun testGetCurrentTermsAndConditions_200() {
        val lang = ""

        webTestClient.method(GET).uri("/api/terms-and-conditions/latest/{lang}", lang)
        .accept(MediaType.APPLICATION_JSON)
        .exchange()
        .expectStatus().isEqualTo(200)
            .expectHeader().contentType(MediaType.APPLICATION_JSON)
            .expectBody()
            .jsonPath("$.id").isNotEmpty()
            .jsonPath("$.version").isNotEmpty()
            .jsonPath("$.content").isNotEmpty()
            .jsonPath("$.lang").isNotEmpty()
            .jsonPath("$.contentVersion").isNotEmpty()
            .jsonPath("$.startDate").isNotEmpty()
        }

        /**
        * Test: acceptTermsAndConditions for OK.
        */
        @Test
        fun testAcceptTermsAndConditions_200() {
        val requestBody = """
            {
              "userId" : 37,
              "termsAndConditionsId" : 11
            }
        """

        webTestClient.method(POST).uri("/api/terms-and-conditions/accept")
        .accept(MediaType.APPLICATION_JSON)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(requestBody)
        .exchange()
        .expectStatus().isEqualTo(200)
        }

}
