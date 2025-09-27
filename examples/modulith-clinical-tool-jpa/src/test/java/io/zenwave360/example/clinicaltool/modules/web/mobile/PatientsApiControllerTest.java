package io.zenwave360.example.clinicaltool.modules.web.mobile;

import io.zenwave360.example.clinicaltool.modules.clinical.config.ServicesInMemoryConfig;
import io.zenwave360.example.clinicaltool.modules.web.mobile.dtos.*;
import java.math.*;
import java.time.*;
import java.util.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Test controller for PatientsApiController.
 */
public class PatientsApiControllerTest {

    private final Logger log = LoggerFactory.getLogger(getClass());

    ServicesInMemoryConfig context = new ServicesInMemoryConfig();

    PatientsApiController controller = new PatientsApiController(context.patientsService());

    @BeforeEach
    void setUp() {
        context.reloadTestData();
    }

    @Test
    public void getPatientProfileByIdTest() {
        Long id = null;
        var response = controller.getPatientProfileById(id);
        Assertions.assertEquals(200, response.getStatusCode().value());
    }

    @Test
    public void requestOptOutTest() {
        Long id = null;
        var response = controller.requestOptOut(id);
        Assertions.assertEquals(200, response.getStatusCode().value());
    }
}
