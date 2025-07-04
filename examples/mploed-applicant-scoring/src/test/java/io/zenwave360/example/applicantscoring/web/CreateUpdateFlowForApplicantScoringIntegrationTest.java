package io.zenwave360.example.applicantscoring.web;

import io.zenwave360.example.applicantscoring.web.*;
import io.zenwave360.example.applicantscoring.web.dtos.*;
import io.zenwave360.example.applicantscoring.web.BaseWebTestClientTest;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.math.BigDecimal;

import static org.springframework.http.HttpMethod.*;

/**
* Business Flow Test for: getApplicationScoring, createApplicantScoring, updateCity, updateBalanceAtBank.
*/
public class CreateUpdateFlowForApplicantScoringIntegrationTest extends BaseWebTestClientTest {

    /**
    * Business Flow Test for: getApplicationScoring, createApplicantScoring, updateCity, updateBalanceAtBank.
    */
    @Test
    public void testCreateUpdateFlowForApplicantScoringIntegrationTest() {


        // createApplicantScoring: createApplicantScoring
        String applicationNumberInputRequestBody1 = """
            {
              "applicationNumber" : "007"
            }
        """;

        var createApplicantScoringResponse1 = webTestClient.method(POST).uri("/api/applicant-scoring")
            .accept(MediaType.APPLICATION_JSON)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(applicationNumberInputRequestBody1)
            .exchange()
            .expectStatus().isEqualTo(201)
            .expectHeader().contentType(MediaType.APPLICATION_JSON)
            .returnResult(ApplicantScoringDTO.class);

        var id = createApplicantScoringResponse1.getResponseBody().blockFirst().getId();

        // updateCity: updateCity
        String cityInputRequestBody2 = """
            {
              "city" : "East Gale"
            }
        """;

        var updateCityResponse2 = webTestClient.method(POST).uri("/api/applicant-scoring/{id}/city", id)
            .accept(MediaType.APPLICATION_JSON)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(cityInputRequestBody2)
            .exchange()
            .expectStatus().isEqualTo(201)
            .expectHeader().contentType(MediaType.APPLICATION_JSON)
            .returnResult(ApplicantScoringDTO.class);


        // updateBalanceAtBank: updateBalanceAtBank
        String balanceAtBankInputRequestBody3 = """
            {
              "balanceAtBank" : 46
            }
        """;

        var updateBalanceAtBankResponse3 = webTestClient.method(POST).uri("/api/applicant-scoring/{id}/balance-at-bank", id)
            .accept(MediaType.APPLICATION_JSON)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(balanceAtBankInputRequestBody3)
            .exchange()
            .expectStatus().isEqualTo(201)
            .expectHeader().contentType(MediaType.APPLICATION_JSON)
            .returnResult(ApplicantScoringDTO.class);

        // getApplicationScoring: getApplicationScoring

        var getApplicationScoringResponse0 = webTestClient.method(GET).uri("/api/applicant-scoring/{id}", id)
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isEqualTo(200)
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .returnResult(ApplicantScoringDTO.class);


    }


}
