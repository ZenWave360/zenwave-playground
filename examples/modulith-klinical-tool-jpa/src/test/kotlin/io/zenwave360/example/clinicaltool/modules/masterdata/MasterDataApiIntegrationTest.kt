package io.zenwave360.example.clinicaltool.modules.masterdata

import io.zenwave360.example.clinicaltool.modules.masterdata.*
import io.zenwave360.example.clinicaltool.modules.masterdata.dtos.*
import io.zenwave360.example.clinicaltool.common.BaseWebTestClientTest

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.test.web.reactive.server.WebTestClient

import java.math.BigDecimal

import org.springframework.http.HttpMethod.*

/**
 * Integration tests for the [MasterDataApi] REST controller.
 */
open class MasterDataApiIntegrationTest : BaseWebTestClientTest() {



        /**
        * Test: createMasterData for OK.
        */
        @Test
        fun testCreateMasterData_201() {
        val requestBody = """
            {
              "id" : 98,
              "version" : 49,
              "type" : "MEDICAL_AREA",
              "key" : "key-7jpxrv9gx1g65b7s9iv4",
              "value" : "value-dkfsujmowesyx5x",
              "translations" : [ {
                "lang" : "lang-wf",
                "text" : "text-bf2e5b07d"
              } ]
            }
        """

        webTestClient.method(POST).uri("/api/masterdata")
        .accept(MediaType.APPLICATION_JSON)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(requestBody)
        .exchange()
        .expectStatus().isEqualTo(201)
            .expectHeader().contentType(MediaType.APPLICATION_JSON)
            .expectBody()
            .jsonPath("$.id").isNotEmpty()
            .jsonPath("$.version").isNotEmpty()
            .jsonPath("$.type").isNotEmpty()
            .jsonPath("$.key").isNotEmpty()
            .jsonPath("$.value").isNotEmpty()
            .jsonPath("$.translations").isNotEmpty()
                .jsonPath("$.translations").isArray()
                    .jsonPath("$.translations[0].lang").isNotEmpty()
                    .jsonPath("$.translations[0].text").isNotEmpty()
        }

        /**
        * Test: listMasterData for OK.
        */
        @Test
        fun testListMasterData_200() {
        val page = ""
        val limit = ""
        val sort = ""

        webTestClient.method(GET).uri({ it.path("/api/masterdata").queryParam("page", page).queryParam("limit", limit).queryParam("sort", sort).build(page, limit, sort) })
        .accept(MediaType.APPLICATION_JSON)
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
                    .jsonPath("$.content[0].type").isNotEmpty()
                    .jsonPath("$.content[0].key").isNotEmpty()
                    .jsonPath("$.content[0].value").isNotEmpty()
                    .jsonPath("$.content[0].translations").isNotEmpty()
        }

        /**
        * Test: getMasterData for OK.
        */
        @Test
        fun testGetMasterData_200() {
        val id = ""

        webTestClient.method(GET).uri("/api/masterdata/{id}", id)
        .accept(MediaType.APPLICATION_JSON)
        .exchange()
        .expectStatus().isEqualTo(200)
            .expectHeader().contentType(MediaType.APPLICATION_JSON)
            .expectBody()
            .jsonPath("$.id").isNotEmpty()
            .jsonPath("$.version").isNotEmpty()
            .jsonPath("$.type").isNotEmpty()
            .jsonPath("$.key").isNotEmpty()
            .jsonPath("$.value").isNotEmpty()
            .jsonPath("$.translations").isNotEmpty()
                .jsonPath("$.translations").isArray()
                    .jsonPath("$.translations[0].lang").isNotEmpty()
                    .jsonPath("$.translations[0].text").isNotEmpty()
        }

        /**
        * Test: updateMasterData for OK.
        */
        @Test
        fun testUpdateMasterData_200() {
        val requestBody = """
            {
              "id" : 90,
              "version" : 97,
              "type" : "GENDER",
              "key" : "key-byj1",
              "value" : "value-0ua",
              "translations" : [ {
                "lang" : "lang-gmcb27m2ucc4otiqct01h",
                "text" : "text-xnp381wtvejrtt"
              } ]
            }
        """
        val id = ""

        webTestClient.method(PUT).uri("/api/masterdata/{id}", id)
        .accept(MediaType.APPLICATION_JSON)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(requestBody)
        .exchange()
        .expectStatus().isEqualTo(200)
            .expectHeader().contentType(MediaType.APPLICATION_JSON)
            .expectBody()
            .jsonPath("$.id").isNotEmpty()
            .jsonPath("$.version").isNotEmpty()
            .jsonPath("$.type").isNotEmpty()
            .jsonPath("$.key").isNotEmpty()
            .jsonPath("$.value").isNotEmpty()
            .jsonPath("$.translations").isNotEmpty()
                .jsonPath("$.translations").isArray()
                    .jsonPath("$.translations[0].lang").isNotEmpty()
                    .jsonPath("$.translations[0].text").isNotEmpty()
        }

        /**
        * Test: deleteMasterData for OK.
        */
        @Test
        fun testDeleteMasterData_204() {
        val id = ""

        webTestClient.method(DELETE).uri("/api/masterdata/{id}", id)
        .accept(MediaType.APPLICATION_JSON)
        .exchange()
        .expectStatus().isEqualTo(204)
        }

        /**
        * Test: listMasterDataOfType for OK.
        */
        @Test
        fun testListMasterDataOfType_200() {
        val type = ""
        val lang = ""

        webTestClient.method(GET).uri("/api/masterdata/type/{type}/{lang}", type, lang)
        .accept(MediaType.APPLICATION_JSON)
        .exchange()
        .expectStatus().isEqualTo(200)
            .expectHeader().contentType(MediaType.APPLICATION_JSON)
            .expectBody()
        }

}
