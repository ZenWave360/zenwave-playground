package io.zenwave360.example.clinicaltool.modules.web.webapp;

import io.zenwave360.example.clinicaltool.modules.clinical.config.ServicesInMemoryConfig;
import io.zenwave360.example.clinicaltool.modules.web.webapp.dtos.*;
import java.math.*;
import java.time.*;
import java.util.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Test controller for HospitalApiController.
 */
public class HospitalApiControllerTest {

    private final Logger log = LoggerFactory.getLogger(getClass());

    ServicesInMemoryConfig context = new ServicesInMemoryConfig();

    HospitalApiController controller = new HospitalApiController(context.hospitalService());

    @BeforeEach
    void setUp() {
        context.reloadTestData();
    }

    @Test
    public void getHospitalTest() {
        Long id = 1L;
        var response = controller.getHospital(id);
        Assertions.assertEquals(200, response.getStatusCode().value());
    }

    @Test
    public void updateHospitalTest() {
        Long id = 1L;
        HospitalDTO reqBody = null;
        var response = controller.updateHospital(id, reqBody);
        Assertions.assertEquals(200, response.getStatusCode().value());
    }

    @Test
    public void createHospitalTest() {
        HospitalDTO reqBody = null;
        var response = controller.createHospital(reqBody);
        Assertions.assertEquals(201, response.getStatusCode().value());
    }

    @Test
    public void listHospitalsTest() {

        var response = controller.listHospitals();
        Assertions.assertEquals(200, response.getStatusCode().value());
    }

    @Test
    public void createDoctorTest() {
        DoctorDTO reqBody = null;
        var response = controller.createDoctor(reqBody);
        Assertions.assertEquals(201, response.getStatusCode().value());
    }

    @Test
    public void listDoctorsTest() {

        var response = controller.listDoctors();
        Assertions.assertEquals(200, response.getStatusCode().value());
    }

    @Test
    public void updateDoctorTest() {
        Long id = 1L;
        DoctorDTO reqBody = null;
        var response = controller.updateDoctor(id, reqBody);
        Assertions.assertEquals(200, response.getStatusCode().value());
    }

    @Test
    public void getDoctorTest() {
        Long id = 1L;
        var response = controller.getDoctor(id);
        Assertions.assertEquals(200, response.getStatusCode().value());
    }

    @Test
    public void listHospitalDoctorsTest() {
        Long hospitalId = 1L;
        var response = controller.listHospitalDoctors(hospitalId);
        Assertions.assertEquals(200, response.getStatusCode().value());
    }

    @Test
    public void listHospitalPatientsTest() {
        Long hospitalId = 1L;
        var response = controller.listHospitalPatients(hospitalId);
        Assertions.assertEquals(200, response.getStatusCode().value());
    }
}
