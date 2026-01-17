package io.zenwave360.example.clinicaltool.modules.clinical.core.implementation;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

import io.zenwave360.example.clinicaltool.modules.clinical.config.*;
import io.zenwave360.example.clinicaltool.modules.clinical.core.domain.*;
import io.zenwave360.example.clinicaltool.modules.clinical.core.implementation.mappers.*;
import io.zenwave360.example.clinicaltool.modules.clinical.core.inbound.*;
import io.zenwave360.example.clinicaltool.modules.clinical.core.inbound.dtos.*;
import io.zenwave360.example.clinicaltool.modules.clinical.core.outbound.jpa.*;
import io.zenwave360.example.clinicaltool.modules.clinical.infrastructure.jpa.inmemory.*;
import java.time.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Acceptance Test for HospitalService.
 */
class HospitalServiceTest {

    private final Logger log = LoggerFactory.getLogger(getClass());

    ServicesInMemoryConfig context = new ServicesInMemoryConfig();
    HospitalServiceImpl hospitalService = context.hospitalService();

    HospitalRepositoryInMemory hospitalRepository = context.hospitalRepository();

    DoctorRepositoryInMemory doctorRepository = context.doctorRepository();

    @BeforeEach
    void setUp() {
        context.reloadTestData();
    }

    @Test
    void getHospitalTest() {
        Long id = 1L;
        var hospital = hospitalService.getHospital(id);
        assertTrue(hospital.isPresent());
    }

    @Test
    void createHospitalTest() {
        Hospital input = new Hospital();
        input.setName("Test Hospital");
        input.setLang("en");
        input.setTimezone("UTC");
        var hospital = hospitalService.createHospital(input);
        assertNotNull(hospital.getId());
        assertTrue(hospitalRepository.containsEntity(hospital));
    }

    @Test
    void updateHospitalTest() {
        Long id = 1L;
        Hospital input = new Hospital();
        input.setName("Updated Hospital");
        input.setLang("es");
        input.setTimezone("ECT");
        var hospital = hospitalService.updateHospital(id, input);
        assertTrue(hospital.isPresent());
        assertTrue(hospitalRepository.containsEntity(hospital.get()));
    }

    @Test
    void listHospitalsTest() {}

    @Test
    void createDoctorTest() {
        Doctor input = new Doctor();
        input.setUserId(1L);
        input.setProfilePictureId(1L);
        input.setHospitalId(1L);
        input.setName("John");
        input.setSurname("Doe");
        input.setSurname2("Smith");
        input.setEmail("john.doe@example.com");
        input.setPhoneNumber("1234567890");
        input.setLang("en");
        var doctor = hospitalService.createDoctor(input);
        assertNotNull(doctor.getId());
        assertTrue(doctorRepository.containsEntity(doctor));
    }

    @Test
    void updateDoctorTest() {
        Long id = 1L;
        Doctor input = new Doctor();
        input.setUserId(1L);
        input.setProfilePictureId(1L);
        input.setHospitalId(1L);
        input.setName("Jane");
        input.setSurname("Doe");
        input.setSurname2("Johnson");
        input.setEmail("jane.doe@example.com");
        input.setPhoneNumber("0987654321");
        input.setLang("es");
        var doctor = hospitalService.updateDoctor(id, input);
        assertTrue(doctor.isPresent());
        assertTrue(doctorRepository.containsEntity(doctor.get()));
    }

    @Test
    void getDoctorTest() {
        Long id = 1L;
        var doctor = hospitalService.getDoctor(id);
        assertTrue(doctor.isPresent());
    }

    @Test
    void listDoctorsTest() {}

    @Test
    void listHospitalDoctorsTest() {}

    @Test
    void listHospitalPatientsTest() {}
}
