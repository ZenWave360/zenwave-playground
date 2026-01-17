package io.zenwave360.example.applicantscoring.web;

import static org.springframework.http.HttpMethod.*;

import io.zenwave360.example.applicantscoring.web.dtos.*;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;

/**
 * Integration tests for the {@link ApplicantScoringApi} REST controller.
 */
public class ApplicantScoringApiIntegrationTest extends BaseWebTestClientTest {

    /**
     * Test: getApplicationScoring for OK.
     */
    @Test
    public void testGetApplicationScoring_200() {
        var id = "1";

        webTestClient
                .method(GET)
                .uri("/api/applicant-scoring/{id}", id)
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus()
                .isEqualTo(200)
                .expectHeader()
                .contentType(MediaType.APPLICATION_JSON)
                .expectBody()
                .jsonPath("$.id")
                .isNotEmpty()
                .jsonPath("$.version")
                .isNotEmpty()
                .jsonPath("$.applicationNumber")
                .isNotEmpty()
                .jsonPath("$.applicationNumber.applicationNumber")
                .isNotEmpty()
                .jsonPath("$.city")
                .isNotEmpty()
                .jsonPath("$.city.city")
                .isNotEmpty()
                .jsonPath("$.balanceAtBank")
                .isNotEmpty()
                .jsonPath("$.balanceAtBank.balanceAtBank")
                .isNotEmpty();
    }

    /**
     * Test: updateApplicantScoring for OK.
     */
    @Test
    public void testUpdateApplicantScoring_200() {
        String requestBody =
                """
            {
              "applicationNumber" : "applicationNumber-93pbr6dx",
              "city" : "Smithamhaven",
              "balanceAtBank" : 88
            }
        """;
        var id = "1";

        webTestClient
                .method(PUT)
                .uri("/api/applicant-scoring/{id}", id)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(requestBody)
                .exchange()
                .expectStatus()
                .isEqualTo(200)
                .expectHeader()
                .contentType(MediaType.APPLICATION_JSON)
                .expectBody()
                .jsonPath("$.id")
                .isNotEmpty()
                .jsonPath("$.version")
                .isNotEmpty()
                .jsonPath("$.applicationNumber")
                .isNotEmpty()
                .jsonPath("$.applicationNumber.applicationNumber")
                .isNotEmpty()
                .jsonPath("$.city")
                .isNotEmpty()
                .jsonPath("$.city.city")
                .isNotEmpty()
                .jsonPath("$.balanceAtBank")
                .isNotEmpty()
                .jsonPath("$.balanceAtBank.balanceAtBank")
                .isNotEmpty();
    }

    /**
     * Test: createApplicantScoring for OK.
     */
    @Test
    public void testCreateApplicantScoring_201() {
        String requestBody =
                """
            {
              "applicationNumber" : "applicationNumber-ljo2wchf"
            }
        """;

        webTestClient
                .method(POST)
                .uri("/api/applicant-scoring")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(requestBody)
                .exchange()
                .expectStatus()
                .isEqualTo(201)
                .expectHeader()
                .contentType(MediaType.APPLICATION_JSON)
                .expectBody()
                .jsonPath("$.id")
                .isNotEmpty()
                .jsonPath("$.version")
                .isNotEmpty()
                .jsonPath("$.applicationNumber")
                .isNotEmpty()
                .jsonPath("$.applicationNumber.applicationNumber")
                .isNotEmpty()
                .jsonPath("$.city")
                .isNotEmpty()
                .jsonPath("$.city.city")
                .isNotEmpty()
                .jsonPath("$.balanceAtBank")
                .isNotEmpty()
                .jsonPath("$.balanceAtBank.balanceAtBank")
                .isNotEmpty();
    }

    /**
     * Test: updateCity for OK.
     */
    @Test
    public void testUpdateCity_201() {
        String requestBody = """
            {
              "city" : "Burmatown"
            }
        """;
        var id = "1";

        webTestClient
                .method(POST)
                .uri("/api/applicant-scoring/{id}/city", id)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(requestBody)
                .exchange()
                .expectStatus()
                .isEqualTo(201)
                .expectHeader()
                .contentType(MediaType.APPLICATION_JSON)
                .expectBody()
                .jsonPath("$.id")
                .isNotEmpty()
                .jsonPath("$.version")
                .isNotEmpty()
                .jsonPath("$.applicationNumber")
                .isNotEmpty()
                .jsonPath("$.applicationNumber.applicationNumber")
                .isNotEmpty()
                .jsonPath("$.city")
                .isNotEmpty()
                .jsonPath("$.city.city")
                .isNotEmpty()
                .jsonPath("$.balanceAtBank")
                .isNotEmpty()
                .jsonPath("$.balanceAtBank.balanceAtBank")
                .isNotEmpty();
    }

    /**
     * Test: updateBalanceAtBank for OK.
     */
    @Test
    public void testUpdateBalanceAtBank_201() {
        String requestBody = """
            {
              "balanceAtBank" : 69
            }
        """;
        var id = "1";

        webTestClient
                .method(POST)
                .uri("/api/applicant-scoring/{id}/balance-at-bank", id)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(requestBody)
                .exchange()
                .expectStatus()
                .isEqualTo(201)
                .expectHeader()
                .contentType(MediaType.APPLICATION_JSON)
                .expectBody()
                .jsonPath("$.id")
                .isNotEmpty()
                .jsonPath("$.version")
                .isNotEmpty()
                .jsonPath("$.applicationNumber")
                .isNotEmpty()
                .jsonPath("$.applicationNumber.applicationNumber")
                .isNotEmpty()
                .jsonPath("$.city")
                .isNotEmpty()
                .jsonPath("$.city.city")
                .isNotEmpty()
                .jsonPath("$.balanceAtBank")
                .isNotEmpty()
                .jsonPath("$.balanceAtBank.balanceAtBank")
                .isNotEmpty();
    }
}
