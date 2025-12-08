package io.zenwave360.example.clinicaltool.modules.documents

import io.zenwave360.example.clinicaltool.modules.documents.*
import io.zenwave360.example.clinicaltool.modules.documents.dtos.*
import io.zenwave360.example.clinicaltool.common.BaseWebTestClientTest

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.test.web.reactive.server.WebTestClient

import java.math.BigDecimal

import org.springframework.http.HttpMethod.*

/**
 * Integration tests for the [DocumentApi] REST controller.
 */
open class DocumentApiIntegrationTest : BaseWebTestClientTest() {



        /**
        * Test: listDocumentInfo for OK.
        */
        @Test
        fun testListDocumentInfo_200() {
        val documentIds = ""

        webTestClient.method(GET).uri({ it.path("/api/documents/").queryParam("documentIds", documentIds).build(documentIds) })
        .accept(MediaType.APPLICATION_JSON)
        .exchange()
        .expectStatus().isEqualTo(200)
            .expectHeader().contentType(MediaType.APPLICATION_JSON)
            .expectBody()
        }

        /**
        * Test: downloadDocument for OK.
        */
        @Test
        fun testDownloadDocument_200() {
        val id = ""
        val preview = ""

        webTestClient.method(GET).uri({ it.path("/api/documents/{id}").queryParam("preview", preview).build(preview) })
        .accept(MediaType.APPLICATION_JSON)
        .exchange()
        .expectStatus().isEqualTo(200)
        }

        /**
        * Test: deleteDocumentInfo for OK.
        */
        @Test
        fun testDeleteDocumentInfo_204() {
        val id = ""

        webTestClient.method(DELETE).uri("/api/documents/{id}", id)
        .accept(MediaType.APPLICATION_JSON)
        .exchange()
        .expectStatus().isEqualTo(204)
        }

        /**
        * Test: uploadDocument for OK.
        */
        @Test
        fun testUploadDocument_201() {
        val uuid = ""
        val tags = ""

        webTestClient.method(POST).uri({ it.path("/api/documents/upload").queryParam("uuid", uuid).queryParam("tags", tags).build(uuid, tags) })
        .accept(MediaType.APPLICATION_JSON)
        .exchange()
        .expectStatus().isEqualTo(201)
            .expectHeader().contentType(MediaType.APPLICATION_JSON)
            .expectBody()
            .jsonPath("$.id").isNotEmpty()
            .jsonPath("$.version").isNotEmpty()
            .jsonPath("$.uuid").isNotEmpty()
            .jsonPath("$.fileName").isNotEmpty()
            .jsonPath("$.documentType").isNotEmpty()
            .jsonPath("$.contentType").isNotEmpty()
            .jsonPath("$.tags").isNotEmpty()
                .jsonPath("$.tags").isArray()
            .jsonPath("$.documentData").isNotEmpty()
                    .jsonPath("$.documentData.id").isNotEmpty()
                    .jsonPath("$.documentData.version").isNotEmpty()
                    .jsonPath("$.documentData.data").isNotEmpty()
        }

}
