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
 * Acceptance Test for PatientsService.
 */
class PatientsServiceTest {

    private final Logger log = LoggerFactory.getLogger(getClass());

    ServicesInMemoryConfig context = new ServicesInMemoryConfig();
    PatientsServiceImpl patientsService = context.patientsService();

    PatientRepositoryInMemory patientRepository = context.patientRepository();

    ProvisionalPatientRepositoryInMemory provisionalPatientRepository = context.provisionalPatientRepository();

    @BeforeEach
    void setUp() {
        context.reloadTestData();
    }

    @Test
    void loadPatientTest() { // TODO: implement this test// TODO: implement this test
    }

    @Test
    void partialPatientUpdateTest() { // TODO: implement this test// TODO: implement this test
    }

    @Test
    void createPatientTest() {
        Patient input = null; // TODO
        // TODO fill input data
        // input.setUserId(0L);
        // input.setSpikeUUID("");
        // input.setHospitalId(0L);
        // input.setProfilePictureId(0L);
        // input.setPhoneNumber("");
        // input.setHisNumber("");
        // input.setEmail("");
        // input.setGeneralInfo(new GeneralInfo());
        // input.setHealthInsuranceInfo(new HealthInsuranceInfo());
        // input.setDocumentIds(List.of(0L));
        var patient = patientsService.createPatient(input);
        assertNotNull(patient.getId());
        assertTrue(patientRepository.containsEntity(patient)); // TODO: implement this test
    }

    @Test
    void updatePatientTest() {
        Long id = null;
        Patient input = null; // TODO
        // TODO fill input data
        // input.setUserId(0L);
        // input.setSpikeUUID("");
        // input.setHospitalId(0L);
        // input.setProfilePictureId(0L);
        // input.setPhoneNumber("");
        // input.setHisNumber("");
        // input.setEmail("");
        // input.setGeneralInfo(new GeneralInfo());
        // input.setHealthInsuranceInfo(new HealthInsuranceInfo());
        // input.setDocumentIds(List.of(0L));
        // assertTrue(patientRepository.containsKey(id));
        var patient = patientsService.updatePatient(id, input);
        assertTrue(patient.isPresent());
        assertTrue(patientRepository.containsEntity(patient.get())); // TODO: implement this test
    }

    @Test
    void getPatientTest() {
        Long id = null;
        var patient = patientsService.getPatient(id);
        assertTrue(patient.isPresent()); // TODO: implement this test
    }

    @Test
    void getPatientProfileByIdTest() { // TODO: implement this test// TODO: implement this test
    }

    @Test
    void requestOptOutTest() { // TODO: implement this test// TODO: implement this test
    }

    @Test
    void associateDocumentWithPatientTest() { // TODO: implement this test// TODO: implement this test
    }
}
