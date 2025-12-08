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
import java.util.List;

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

    @BeforeEach
    void setUp() {
        context.reloadTestData();
    }

    @Test
    void loadPatientTest() {
    }

    @Test
    void partialPatientUpdateTest() {
    }

    @Test
    void createPatientTest() {
        Patient input = new Patient();
        input.setUserId(1L);
        input.setHospitalId(1L);
        input.setProfilePictureId(1L);
        input.setPhoneNumber("1234567890");
        input.setHisNumber("HIS123456");
        input.setEmail("patient@example.com");

        GeneralInfo generalInfo = new GeneralInfo();
        generalInfo.setName("John");
        generalInfo.setSurname("Doe");
        generalInfo.setSurname2("Smith");
        generalInfo.setIdentityDocumentType(IdentityDocumentType.DNI);
        generalInfo.setIdentityDocumentNumber("12345678A");
        generalInfo.setBirthDate(LocalDate.of(1990, 1, 1));
        generalInfo.setGender(GenderType.MALE);
        generalInfo.setLang("en");
        input.setGeneralInfo(generalInfo);

        HealthInsuranceInfo healthInsuranceInfo = new HealthInsuranceInfo();
        healthInsuranceInfo.setInsuranceCompanyId("INS123");
        healthInsuranceInfo.setInsuranceCardNumber("CARD123456");
        input.setHealthInsuranceInfo(healthInsuranceInfo);

        input.setDocumentIds(List.of(1L, 2L, 3L));
        var patient = patientsService.createPatient(input);
        assertNotNull(patient.getId());
        assertTrue(patientRepository.containsEntity(patient));
    }

    @Test
    void updatePatientTest() {
        Long id = 1L;
        Patient input = new Patient();
        input.setUserId(1L);
        input.setHospitalId(1L);
        input.setProfilePictureId(1L);
        input.setPhoneNumber("0987654321");
        input.setHisNumber("HIS654321");
        input.setEmail("updated.patient@example.com");

        GeneralInfo generalInfo = new GeneralInfo();
        generalInfo.setName("Jane");
        generalInfo.setSurname("Doe");
        generalInfo.setSurname2("Johnson");
        generalInfo.setIdentityDocumentType(IdentityDocumentType.PASSPORT);
        generalInfo.setIdentityDocumentNumber("P87654321");
        generalInfo.setBirthDate(LocalDate.of(1985, 5, 15));
        generalInfo.setGender(GenderType.FEMALE);
        generalInfo.setLang("es");
        input.setGeneralInfo(generalInfo);

        HealthInsuranceInfo healthInsuranceInfo = new HealthInsuranceInfo();
        healthInsuranceInfo.setInsuranceCompanyId("INS456");
        healthInsuranceInfo.setInsuranceCardNumber("CARD654321");
        input.setHealthInsuranceInfo(healthInsuranceInfo);

        input.setDocumentIds(List.of(4L, 5L));
        var patient = patientsService.updatePatient(id, input);
        assertTrue(patient.isPresent());
        assertTrue(patientRepository.containsEntity(patient.get()));
    }

    @Test
    void getPatientTest() {
        Long id = 1L;
        var patient = patientsService.getPatient(id);
        assertTrue(patient.isPresent());
    }

    @Test
    void getPatientProfileByIdTest() {
    }

    @Test
    void requestOptOutTest() {
    }

    @Test
    void associateDocumentWithPatientTest() {
    }
}
