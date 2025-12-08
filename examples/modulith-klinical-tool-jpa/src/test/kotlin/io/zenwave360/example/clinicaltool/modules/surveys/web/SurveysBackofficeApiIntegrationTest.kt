package io.zenwave360.example.clinicaltool.modules.surveys.web

import io.zenwave360.example.clinicaltool.modules.surveys.web.*
import io.zenwave360.example.clinicaltool.modules.surveys.web.dtos.*
import io.zenwave360.example.clinicaltool.common.web.BaseWebTestClientTest

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.test.web.reactive.server.WebTestClient

import java.math.BigDecimal

import org.springframework.http.HttpMethod.*

/**
 * Integration tests for the [SurveysBackofficeApi] REST controller.
 */
open class SurveysBackofficeApiIntegrationTest : BaseWebTestClientTest() {



        /**
        * Test: listSurveys for OK.
        */
        @Test
        fun testListSurveys_200() {

        webTestClient.method(GET).uri("/api/backoffice/surveys")
        .accept(MediaType.APPLICATION_JSON)
        .exchange()
        .expectStatus().isEqualTo(200)
            .expectHeader().contentType(MediaType.APPLICATION_JSON)
            .expectBody()
        }

        /**
        * Test: createSurvey for OK.
        */
        @Test
        fun testCreateSurvey_201() {
        val requestBody = """
            {
              "id" : 43,
              "version" : 32,
              "name" : "name-m7sdii",
              "hospitalId" : 35,
              "title" : "title-xdfcaz9cr",
              "lang" : "lang-q9ksp",
              "sections" : [ {
                "name" : "name-xnk6u0fqqegc0m4bii8",
                "translations" : [ {
                  "lang" : "lang-7fa2",
                  "title" : "title-gmyhsgdejcvnq985e4ul",
                  "summary" : "summary-4"
                } ],
                "questionIds" : [ 83 ]
              } ]
            }
        """

        webTestClient.method(POST).uri("/api/backoffice/surveys")
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
            .jsonPath("$.hospitalId").isNotEmpty()
            .jsonPath("$.title").isNotEmpty()
            .jsonPath("$.lang").isNotEmpty()
            .jsonPath("$.sections").isNotEmpty()
                .jsonPath("$.sections").isArray()
                    .jsonPath("$.sections[0].name").isNotEmpty()
                    .jsonPath("$.sections[0].translations").isNotEmpty()
                    .jsonPath("$.sections[0].questionIds").isNotEmpty()
        }

        /**
        * Test: getSurvey for OK.
        */
        @Test
        fun testGetSurvey_200() {
        val id = ""

        webTestClient.method(GET).uri("/api/backoffice/surveys/{id}", id)
        .accept(MediaType.APPLICATION_JSON)
        .exchange()
        .expectStatus().isEqualTo(200)
            .expectHeader().contentType(MediaType.APPLICATION_JSON)
            .expectBody()
            .jsonPath("$.id").isNotEmpty()
            .jsonPath("$.version").isNotEmpty()
            .jsonPath("$.name").isNotEmpty()
            .jsonPath("$.hospitalId").isNotEmpty()
            .jsonPath("$.title").isNotEmpty()
            .jsonPath("$.lang").isNotEmpty()
            .jsonPath("$.sections").isNotEmpty()
                .jsonPath("$.sections").isArray()
                    .jsonPath("$.sections[0].name").isNotEmpty()
                    .jsonPath("$.sections[0].translations").isNotEmpty()
                    .jsonPath("$.sections[0].questionIds").isNotEmpty()
        }

        /**
        * Test: updateSurvey for OK.
        */
        @Test
        fun testUpdateSurvey_200() {
        val requestBody = """
            {
              "id" : 10,
              "version" : 86,
              "name" : "name-2rohwh5pe",
              "hospitalId" : 49,
              "title" : "title-mvkhrsjv9uh6jdqe6qid",
              "lang" : "lang-5gkqnhe3fa6hnluee8f",
              "sections" : [ {
                "name" : "name-r2dhd7kzu8gaql6tg8u8c",
                "translations" : [ {
                  "lang" : "lang-536gskzwk6yy",
                  "title" : "title-0hr",
                  "summary" : "summary-9kgxbqba16dem6lk22"
                } ],
                "questionIds" : [ 2 ]
              } ]
            }
        """
        val id = ""

        webTestClient.method(PUT).uri("/api/backoffice/surveys/{id}", id)
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
            .jsonPath("$.hospitalId").isNotEmpty()
            .jsonPath("$.title").isNotEmpty()
            .jsonPath("$.lang").isNotEmpty()
            .jsonPath("$.sections").isNotEmpty()
                .jsonPath("$.sections").isArray()
                    .jsonPath("$.sections[0].name").isNotEmpty()
                    .jsonPath("$.sections[0].translations").isNotEmpty()
                    .jsonPath("$.sections[0].questionIds").isNotEmpty()
        }

        /**
        * Test: deleteSurvey for OK.
        */
        @Test
        fun testDeleteSurvey_204() {
        val id = ""

        webTestClient.method(DELETE).uri("/api/backoffice/surveys/{id}", id)
        .accept(MediaType.APPLICATION_JSON)
        .exchange()
        .expectStatus().isEqualTo(204)
        }

        /**
        * Test: listQuestions for OK.
        */
        @Test
        fun testListQuestions_200() {

        webTestClient.method(GET).uri("/api/backoffice/surveys/questions")
        .accept(MediaType.APPLICATION_JSON)
        .exchange()
        .expectStatus().isEqualTo(200)
            .expectHeader().contentType(MediaType.APPLICATION_JSON)
            .expectBody()
        }

        /**
        * Test: createQuestion for OK.
        */
        @Test
        fun testCreateQuestion_201() {
        val requestBody = """
            {
              "id" : 76,
              "version" : 73,
              "name" : "name-jy5sa",
              "questionType" : "SINGLE_SELECTION",
              "required" : true,
              "rangeStart" : 69,
              "rangeEnd" : 77,
              "translations" : [ {
                "lang" : "lang-gtj9n44j7cbp5rg",
                "text" : "text-lq5tnmjwi5"
              } ],
              "options" : [ {
                "name" : "name-eqxqj0spn1htvnfya",
                "translations" : [ {
                  "lang" : "lang-1cvx",
                  "text" : "text-f"
                } ]
              } ],
              "includeOther" : true
            }
        """

        webTestClient.method(POST).uri("/api/backoffice/surveys/questions")
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
            .jsonPath("$.questionType").isNotEmpty()
            .jsonPath("$.required").isNotEmpty()
            .jsonPath("$.rangeStart").isNotEmpty()
            .jsonPath("$.rangeEnd").isNotEmpty()
            .jsonPath("$.translations").isNotEmpty()
                .jsonPath("$.translations").isArray()
                    .jsonPath("$.translations[0].lang").isNotEmpty()
                    .jsonPath("$.translations[0].text").isNotEmpty()
            .jsonPath("$.options").isNotEmpty()
                .jsonPath("$.options").isArray()
                    .jsonPath("$.options[0].name").isNotEmpty()
                    .jsonPath("$.options[0].translations").isNotEmpty()
            .jsonPath("$.includeOther").isNotEmpty()
        }

        /**
        * Test: getQuestion for OK.
        */
        @Test
        fun testGetQuestion_200() {
        val id = ""

        webTestClient.method(GET).uri("/api/backoffice/surveys/questions/{id}", id)
        .accept(MediaType.APPLICATION_JSON)
        .exchange()
        .expectStatus().isEqualTo(200)
            .expectHeader().contentType(MediaType.APPLICATION_JSON)
            .expectBody()
            .jsonPath("$.id").isNotEmpty()
            .jsonPath("$.version").isNotEmpty()
            .jsonPath("$.name").isNotEmpty()
            .jsonPath("$.questionType").isNotEmpty()
            .jsonPath("$.required").isNotEmpty()
            .jsonPath("$.rangeStart").isNotEmpty()
            .jsonPath("$.rangeEnd").isNotEmpty()
            .jsonPath("$.translations").isNotEmpty()
                .jsonPath("$.translations").isArray()
                    .jsonPath("$.translations[0].lang").isNotEmpty()
                    .jsonPath("$.translations[0].text").isNotEmpty()
            .jsonPath("$.options").isNotEmpty()
                .jsonPath("$.options").isArray()
                    .jsonPath("$.options[0].name").isNotEmpty()
                    .jsonPath("$.options[0].translations").isNotEmpty()
            .jsonPath("$.includeOther").isNotEmpty()
        }

        /**
        * Test: updateQuestion for OK.
        */
        @Test
        fun testUpdateQuestion_200() {
        val requestBody = """
            {
              "id" : 80,
              "version" : 51,
              "name" : "name-0uc4flx50c2vmrvi9y",
              "questionType" : "DECIMAL",
              "required" : true,
              "rangeStart" : 55,
              "rangeEnd" : 11,
              "translations" : [ {
                "lang" : "lang-eelhpr6tue",
                "text" : "text-2r9ovoplm1shpuyz4do"
              } ],
              "options" : [ {
                "name" : "name-vtds",
                "translations" : [ {
                  "lang" : "lang-ql4aufemrjz",
                  "text" : "text-qpch825nbcjlue9w3ek91"
                } ]
              } ],
              "includeOther" : true
            }
        """
        val id = ""

        webTestClient.method(PUT).uri("/api/backoffice/surveys/questions/{id}", id)
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
            .jsonPath("$.questionType").isNotEmpty()
            .jsonPath("$.required").isNotEmpty()
            .jsonPath("$.rangeStart").isNotEmpty()
            .jsonPath("$.rangeEnd").isNotEmpty()
            .jsonPath("$.translations").isNotEmpty()
                .jsonPath("$.translations").isArray()
                    .jsonPath("$.translations[0].lang").isNotEmpty()
                    .jsonPath("$.translations[0].text").isNotEmpty()
            .jsonPath("$.options").isNotEmpty()
                .jsonPath("$.options").isArray()
                    .jsonPath("$.options[0].name").isNotEmpty()
                    .jsonPath("$.options[0].translations").isNotEmpty()
            .jsonPath("$.includeOther").isNotEmpty()
        }

        /**
        * Test: deleteQuestion for OK.
        */
        @Test
        fun testDeleteQuestion_204() {
        val id = ""

        webTestClient.method(DELETE).uri("/api/backoffice/surveys/questions/{id}", id)
        .accept(MediaType.APPLICATION_JSON)
        .exchange()
        .expectStatus().isEqualTo(204)
        }

}
