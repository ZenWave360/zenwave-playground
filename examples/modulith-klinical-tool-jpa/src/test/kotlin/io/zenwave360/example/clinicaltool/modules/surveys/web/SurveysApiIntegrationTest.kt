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
 * Integration tests for the [SurveysApi] REST controller.
 */
open class SurveysApiIntegrationTest : BaseWebTestClientTest() {



        /**
        * Test: getSurveyAndQuestionsForPatient for OK.
        */
        @Test
        fun testGetSurveyAndQuestionsForPatient_200() {
        val name = ""
        val patientId = ""
        val lang = ""

        webTestClient.method(GET).uri({ it.path("/api/public/surveys/{name}/patient/{patientId}/questions").queryParam("lang", lang).build(lang) })
        .accept(MediaType.APPLICATION_JSON)
        .exchange()
        .expectStatus().isEqualTo(200)
            .expectHeader().contentType(MediaType.APPLICATION_JSON)
            .expectBody()
            .jsonPath("$.surveyId").isNotEmpty()
            .jsonPath("$.name").isNotEmpty()
            .jsonPath("$.title").isNotEmpty()
            .jsonPath("$.hospitalId").isNotEmpty()
            .jsonPath("$.lang").isNotEmpty()
            .jsonPath("$.sections").isNotEmpty()
                .jsonPath("$.sections").isArray()
                    .jsonPath("$.sections[0].name").isNotEmpty()
                    .jsonPath("$.sections[0].title").isNotEmpty()
                    .jsonPath("$.sections[0].summary").isNotEmpty()
                    .jsonPath("$.sections[0].questions").isNotEmpty()
        }

        /**
        * Test: answerSurvey for OK.
        */
        @Test
        fun testAnswerSurvey_201() {
        val requestBody = """
            {
              "id" : 44,
              "version" : 36,
              "surveyId" : 34,
              "patientId" : 38,
              "date" : "2025-12-08",
              "lang" : "lang-rso5g",
              "answers" : [ {
                "questionId" : 9,
                "value" : "value-2esc2rh",
                "values" : [ "values-b33fcb5zic5c2uo63jr" ],
                "otherValue" : "otherValue-iz362autcyfag"
              } ]
            }
        """
        val surveyId = ""
        val patientId = ""
        val date = ""

        webTestClient.method(POST).uri("/api/public/surveys/{surveyId}/patient/{patientId}/answers/{date}", surveyId, patientId, date)
        .accept(MediaType.APPLICATION_JSON)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(requestBody)
        .exchange()
        .expectStatus().isEqualTo(201)
            .expectHeader().contentType(MediaType.APPLICATION_JSON)
            .expectBody()
            .jsonPath("$.id").isNotEmpty()
            .jsonPath("$.version").isNotEmpty()
            .jsonPath("$.surveyId").isNotEmpty()
            .jsonPath("$.patientId").isNotEmpty()
            .jsonPath("$.date").isNotEmpty()
            .jsonPath("$.lang").isNotEmpty()
            .jsonPath("$.answers").isNotEmpty()
                .jsonPath("$.answers").isArray()
                    .jsonPath("$.answers[0].questionId").isNotEmpty()
                    .jsonPath("$.answers[0].value").isNotEmpty()
                    .jsonPath("$.answers[0].values").isNotEmpty()
                    .jsonPath("$.answers[0].otherValue").isNotEmpty()
        }

        /**
        * Test: updateSurveyAnswers for OK.
        */
        @Test
        fun testUpdateSurveyAnswers_200() {
        val requestBody = """
            {
              "id" : 57,
              "version" : 36,
              "surveyId" : 72,
              "patientId" : 6,
              "date" : "2025-12-08",
              "lang" : "lang-tqbqlh3zh8q",
              "answers" : [ {
                "questionId" : 91,
                "value" : "value-rcvpwbph75w",
                "values" : [ "values-o5a6ndnq69twsa3t3s" ],
                "otherValue" : "otherValue-nlydjzmt"
              } ]
            }
        """
        val surveyId = ""
        val patientId = ""
        val date = ""

        webTestClient.method(PATCH).uri("/api/public/surveys/{surveyId}/patient/{patientId}/answers/{date}", surveyId, patientId, date)
        .accept(MediaType.APPLICATION_JSON)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(requestBody)
        .exchange()
        .expectStatus().isEqualTo(200)
            .expectHeader().contentType(MediaType.APPLICATION_JSON)
            .expectBody()
            .jsonPath("$.id").isNotEmpty()
            .jsonPath("$.version").isNotEmpty()
            .jsonPath("$.surveyId").isNotEmpty()
            .jsonPath("$.patientId").isNotEmpty()
            .jsonPath("$.date").isNotEmpty()
            .jsonPath("$.lang").isNotEmpty()
            .jsonPath("$.answers").isNotEmpty()
                .jsonPath("$.answers").isArray()
                    .jsonPath("$.answers[0].questionId").isNotEmpty()
                    .jsonPath("$.answers[0].value").isNotEmpty()
                    .jsonPath("$.answers[0].values").isNotEmpty()
                    .jsonPath("$.answers[0].otherValue").isNotEmpty()
        }

        /**
        * Test: getSurveyAnswers for OK.
        */
        @Test
        fun testGetSurveyAnswers_200() {
        val surveyId = ""
        val patientId = ""
        val date = ""

        webTestClient.method(GET).uri("/api/public/surveys/{surveyId}/patient/{patientId}/answers/{date}", surveyId, patientId, date)
        .accept(MediaType.APPLICATION_JSON)
        .exchange()
        .expectStatus().isEqualTo(200)
            .expectHeader().contentType(MediaType.APPLICATION_JSON)
            .expectBody()
            .jsonPath("$.id").isNotEmpty()
            .jsonPath("$.version").isNotEmpty()
            .jsonPath("$.surveyId").isNotEmpty()
            .jsonPath("$.patientId").isNotEmpty()
            .jsonPath("$.date").isNotEmpty()
            .jsonPath("$.lang").isNotEmpty()
            .jsonPath("$.answers").isNotEmpty()
                .jsonPath("$.answers").isArray()
                    .jsonPath("$.answers[0].questionId").isNotEmpty()
                    .jsonPath("$.answers[0].value").isNotEmpty()
                    .jsonPath("$.answers[0].values").isNotEmpty()
                    .jsonPath("$.answers[0].otherValue").isNotEmpty()
        }

}
