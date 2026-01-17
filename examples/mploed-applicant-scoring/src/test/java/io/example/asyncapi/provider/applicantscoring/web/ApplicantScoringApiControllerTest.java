package io.example.asyncapi.provider.applicantscoring.web;

import io.zenwave360.example.applicantscoring.config.ServicesInMemoryConfig;
import io.zenwave360.example.applicantscoring.web.*;
import io.zenwave360.example.applicantscoring.web.dtos.*;
import java.math.*;
import java.time.*;
import java.util.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Test controller for ApplicantScoringApiController.
 */
public class ApplicantScoringApiControllerTest {

    private final Logger log = LoggerFactory.getLogger(getClass());

    ServicesInMemoryConfig context = new ServicesInMemoryConfig();

    ApplicantScoringApiController controller = new ApplicantScoringApiController(context.applicantScoringService());

    @BeforeEach
    void setUp() {
        context.reloadTestData();
    }

    @Test
    public void getApplicationScoringTest() {
        Long id = null;
        var response = controller.getApplicationScoring(id);
        Assertions.assertEquals(200, response.getStatusCode().value());
    }

    @Test
    public void updateApplicantScoringTest() {
        Long id = null;
        ApplicantScoringInputDTO reqBody = null;
        var response = controller.updateApplicantScoring(id, reqBody);
        Assertions.assertEquals(200, response.getStatusCode().value());
    }

    @Test
    public void createApplicantScoringTest() {
        ApplicationNumberInputDTO reqBody = null;
        var response = controller.createApplicantScoring(reqBody);
        Assertions.assertEquals(201, response.getStatusCode().value());
    }

    @Test
    public void updateCityTest() {
        Long id = null;
        CityInputDTO reqBody = null;
        var response = controller.updateCity(id, reqBody);
        Assertions.assertEquals(201, response.getStatusCode().value());
    }

    @Test
    public void updateBalanceAtBankTest() {
        Long id = null;
        BalanceAtBankInputDTO reqBody = null;
        var response = controller.updateBalanceAtBank(id, reqBody);
        Assertions.assertEquals(201, response.getStatusCode().value());
    }
}
