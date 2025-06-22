package io.zenwave360.example.clinicaltool.adapters.web.webapp;

import io.zenwave360.example.clinicaltool.adapters.web.webapp.*;
import io.zenwave360.example.clinicaltool.adapters.web.webapp.dtos.*;
import io.zenwave360.example.clinicaltool.modules.clinical.config.ServicesInMemoryConfig;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.*;
import java.time.*;
import java.util.*;

/**
 * Test controller for PatientsApiController.
 */
public class PatientsApiControllerTest {

    private final Logger log = LoggerFactory.getLogger(getClass());

    ServicesInMemoryConfig context = new ServicesInMemoryConfig();

    PatientsApiController controller = new PatientsApiController( context.patientsService() );

	@BeforeEach
	void setUp() {
		context.reloadTestData();
	}


    @Test
    public void loadPatientTest() {
        String hisNumber = null;
String phoneNumber = null;
        var response = controller.loadPatient(hisNumber, phoneNumber);
        Assertions.assertEquals(200, response.getStatusCode().value());
    }

    @Test
    public void partialPatientUpdateTest() {
        String hisNumber = null;
String phoneNumber = null;
Map input = null;
        var response = controller.partialPatientUpdate(hisNumber, phoneNumber, input);
        Assertions.assertEquals(200, response.getStatusCode().value());
    }

    @Test
    public void createPatientTest() {
        PatientDTO reqBody = null;
        var response = controller.createPatient(reqBody);
        Assertions.assertEquals(201, response.getStatusCode().value());
    }

    @Test
    public void updatePatientTest() {
        Long id = null;
PatientDTO reqBody = null;
        var response = controller.updatePatient(id, reqBody);
        Assertions.assertEquals(200, response.getStatusCode().value());
    }

    @Test
    public void getPatientTest() {
        Long id = null;
        var response = controller.getPatient(id);
        Assertions.assertEquals(200, response.getStatusCode().value());
    }

    @Test
    public void requestOptOutTest() {
        Long id = null;
        var response = controller.requestOptOut(id);
        Assertions.assertEquals(200, response.getStatusCode().value());
    }


}
