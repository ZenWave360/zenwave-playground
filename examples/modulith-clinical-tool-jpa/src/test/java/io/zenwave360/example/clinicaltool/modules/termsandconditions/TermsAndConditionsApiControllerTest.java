package io.zenwave360.example.clinicaltool.modules.termsandconditions;

import io.zenwave360.example.clinicaltool.modules.termsandconditions.config.ServicesInMemoryConfig;
import io.zenwave360.example.clinicaltool.modules.termsandconditions.dtos.*;
import java.math.*;
import java.time.*;
import java.util.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Test controller for TermsAndConditionsApiController.
 */
public class TermsAndConditionsApiControllerTest {

    private final Logger log = LoggerFactory.getLogger(getClass());

    ServicesInMemoryConfig context = new ServicesInMemoryConfig();

    TermsAndConditionsApiController controller =
            new TermsAndConditionsApiController(context.termsAndConditionsService());

    @BeforeEach
    void setUp() {
        context.reloadTestData();
    }

    @Test
    public void listTermsAndConditionsTest() {

        var response = controller.listTermsAndConditions();
        Assertions.assertEquals(200, response.getStatusCode().value());
    }

    @Test
    public void createTermsAndConditionsTest() {
        TermsAndConditionsDTO reqBody = null;
        var response = controller.createTermsAndConditions(reqBody);
        Assertions.assertEquals(201, response.getStatusCode().value());
    }

    @Test
    public void getTermsAndConditionsTest() {
        Long id = null;
        var response = controller.getTermsAndConditions(id);
        Assertions.assertEquals(200, response.getStatusCode().value());
    }

    @Test
    public void updateTermsAndConditionsTest() {
        Long id = null;
        TermsAndConditionsDTO reqBody = null;
        var response = controller.updateTermsAndConditions(id, reqBody);
        Assertions.assertEquals(200, response.getStatusCode().value());
    }

    @Test
    public void getCurrentTermsAndConditionsTest() {
        String lang = null;
        var response = controller.getCurrentTermsAndConditions(lang);
        Assertions.assertEquals(200, response.getStatusCode().value());
    }

    @Test
    public void acceptTermsAndConditionsTest() {
        AcceptedTermsAndConditionsInputDTO reqBody = null;
        var response = controller.acceptTermsAndConditions(reqBody);
        Assertions.assertEquals(200, response.getStatusCode().value());
    }
}
