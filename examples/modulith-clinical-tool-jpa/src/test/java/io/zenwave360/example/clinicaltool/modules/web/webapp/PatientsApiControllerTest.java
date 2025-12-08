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
    public void loadPatientTest() {
        String hisNumber = "1234567890";
        String phoneNumber = "1234567890";
        var response = controller.loadPatient(hisNumber, phoneNumber);
        Assertions.assertEquals(200, response.getStatusCode().value());
    }

    @Test
    public void partialPatientUpdateTest() {
        String hisNumber = "1234567890";
        String phoneNumber = "1234567890";
        Map<String, Object> input = new HashMap<>();
        input.put("email", "test@example.com");
        input.put("generalInfo.name", "John");
        input.put("generalInfo.surname", "Doe");
        input.put("generalInfo.surname2", "Smith");
        input.put("generalInfo.identityDocumentType", "DNI");
        input.put("generalInfo.identityDocumentNumber", "1234567890");
        input.put("generalInfo.birthDate", LocalDate.of(1990, 1, 1));
        input.put("generalInfo.gender", "MALE");
        input.put("generalInfo.lang", "en");
        input.put("healthInsuranceInfo.insuranceCompanyId", "1234567890");
        input.put("healthInsuranceInfo.insuranceCardNumber", "1234567890");
        input.put("documentIds", List.of(1L, 2L, 3L));

        var response = controller.partialPatientUpdate(hisNumber, phoneNumber, input);
        Assertions.assertEquals(200, response.getStatusCode().value());
    }

    @Test
    public void createPatientTest() {
        PatientDTO reqBody = new PatientDTO();
        reqBody.setUserId(1L);
        reqBody.setHospitalId(1L);
        reqBody.setProfilePictureId(1L);
        reqBody.setPhoneNumber("1234567890");
        reqBody.setHisNumber("1234567890");
        reqBody.setEmail("test@example.com");

        GeneralInfoDTO generalInfo = new GeneralInfoDTO();
        generalInfo.setName("John");
        generalInfo.setSurname("Doe");
        generalInfo.setSurname2("Smith");
        generalInfo.setIdentityDocumentType(IdentityDocumentTypeDTO.DNI);
        generalInfo.setIdentityDocumentNumber("1234567890");
        generalInfo.setBirthDate(LocalDate.of(1990, 1, 1));
        generalInfo.setGender(GenderTypeDTO.MALE);
        generalInfo.setLang("en");
        reqBody.setGeneralInfo(generalInfo);

        HealthInsuranceInfoDTO healthInsuranceInfo = new HealthInsuranceInfoDTO();
        healthInsuranceInfo.setInsuranceCompanyId("1234567890");
        healthInsuranceInfo.setInsuranceCardNumber("1234567890");
        reqBody.setHealthInsuranceInfo(healthInsuranceInfo);

        reqBody.setDocumentIds(List.of(1L, 2L, 3L));

        var response = controller.createPatient(reqBody);
        Assertions.assertEquals(201, response.getStatusCode().value());
    }

    @Test
    public void updatePatientTest() {
        Long id = 1L;
        PatientDTO reqBody = new PatientDTO();
        reqBody.setUserId(1L);
        reqBody.setHospitalId(1L);
        reqBody.setProfilePictureId(1L);
        reqBody.setPhoneNumber("1234567890");
        reqBody.setHisNumber("1234567890");
        reqBody.setEmail("test@example.com");

        GeneralInfoDTO generalInfo = new GeneralInfoDTO();
        generalInfo.setName("John");
        generalInfo.setSurname("Doe");
        generalInfo.setSurname2("Smith");
        generalInfo.setIdentityDocumentType(IdentityDocumentTypeDTO.DNI);
        generalInfo.setIdentityDocumentNumber("1234567890");
        generalInfo.setBirthDate(LocalDate.of(1990, 1, 1));
        generalInfo.setGender(GenderTypeDTO.MALE);
        generalInfo.setLang("en");
        reqBody.setGeneralInfo(generalInfo);

        HealthInsuranceInfoDTO healthInsuranceInfo = new HealthInsuranceInfoDTO();
        healthInsuranceInfo.setInsuranceCompanyId("1234567890");
        healthInsuranceInfo.setInsuranceCardNumber("1234567890");
        reqBody.setHealthInsuranceInfo(healthInsuranceInfo);

        reqBody.setDocumentIds(List.of(1L, 2L, 3L));

        var response = controller.updatePatient(id, reqBody);
        Assertions.assertEquals(200, response.getStatusCode().value());
    }

    @Test
    public void getPatientTest() {
        Long id = 1L;
        var response = controller.getPatient(id);
        Assertions.assertEquals(200, response.getStatusCode().value());
    }

}
