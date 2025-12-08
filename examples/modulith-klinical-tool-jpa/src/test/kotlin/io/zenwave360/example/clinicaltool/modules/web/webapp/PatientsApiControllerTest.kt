package io.zenwave360.example.clinicaltool.modules.web.webapp

import io.zenwave360.example.clinicaltool.modules.web.webapp.*
import io.zenwave360.example.clinicaltool.modules.web.webapp.dtos.*
import io.zenwave360.example.clinicaltool.modules.clinical.config.ServicesInMemoryConfig

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.slf4j.Logger
import org.slf4j.LoggerFactory

import java.math.*
import java.time.*
import java.util.*

/**
 * Test controller for PatientsApiController.
 */
class PatientsApiControllerTest {

    private val log: Logger = LoggerFactory.getLogger(javaClass)

    private val context = ServicesInMemoryConfig()

    private val controller = PatientsApiController( context.patientsService() )

    @BeforeEach
    fun setUp() {
        context.reloadTestData()
    }


    @Test
    fun loadPatientTest() {
        val hisNumber: String = "1234567890"
        val phoneNumber: String = "1234567890"
        val response = controller.loadPatient(hisNumber, phoneNumber)
        Assertions.assertEquals(200, response.statusCode.value())
    }

    @Test
    fun partialPatientUpdateTest() {
        val hisNumber: String = "1234567890"
        val phoneNumber: String = "1234567890"
        val input: MutableMap<String, Any?> = mutableMapOf()
        input["email"] = "test@example.com"
        input["generalInfo.name"] = "John"
        input["generalInfo.surname"] = "Doe"
        input["generalInfo.surname2"] = "Smith"
        input["generalInfo.identityDocumentType"] = "DNI"
        input["generalInfo.identityDocumentNumber"] = "1234567890"
        input["generalInfo.birthDate"] = LocalDate.of(1990, 1, 1)
        input["generalInfo.gender"] = "MALE"
        input["generalInfo.lang"] = "en"
        input["healthInsuranceInfo.insuranceCompanyId"] = "1234567890"
        input["healthInsuranceInfo.insuranceCardNumber"] = "1234567890"
        input["documentIds"] = mutableListOf(1L, 2L, 3L)

        val response = controller.partialPatientUpdate(hisNumber, phoneNumber, input)
        Assertions.assertEquals(200, response.statusCode.value())
    }

    @Test
    fun createPatientTest() {
        val reqBody = PatientDTO(
            userId = 1L,
            hospitalId = 1L,
            profilePictureId = 1L,
            phoneNumber = "1234567890",
            hisNumber = "1234567890",
            email = "test@example.com",
            generalInfo = GeneralInfoDTO(
                name = "John",
                surname = "Doe",
                surname2 = "Smith",
                identityDocumentType = IdentityDocumentTypeDTO.DNI,
                identityDocumentNumber = "1234567890",
                birthDate = LocalDate.of(1990, 1, 1),
                gender = GenderTypeDTO.MALE,
                lang = "en"
            ),
            healthInsuranceInfo = HealthInsuranceInfoDTO(
                insuranceCompanyId = "1234567890",
                insuranceCardNumber = "1234567890"
            ),
            documentIds = mutableListOf(1L, 2L, 3L)
        )
        val response = controller.createPatient(reqBody)
        Assertions.assertEquals(201, response.statusCode.value())
    }

    @Test
    fun updatePatientTest() {
        val id: Long = 1L
        val reqBody = PatientDTO(
            userId = 1L,
            hospitalId = 1L,
            profilePictureId = 1L,
            phoneNumber = "1234567890",
            hisNumber = "1234567890",
            email = "test@example.com",
            generalInfo = GeneralInfoDTO(
                name = "John",
                surname = "Doe",
                surname2 = "Smith",
                identityDocumentType = IdentityDocumentTypeDTO.DNI,
                identityDocumentNumber = "1234567890",
                birthDate = LocalDate.of(1990, 1, 1),
                gender = GenderTypeDTO.MALE,
                lang = "en"
            ),
            healthInsuranceInfo = HealthInsuranceInfoDTO(
                insuranceCompanyId = "1234567890",
                insuranceCardNumber = "1234567890"
            ),
            documentIds = mutableListOf(1L, 2L, 3L)
        )
        val response = controller.updatePatient(id, reqBody)
        Assertions.assertEquals(200, response.statusCode.value())
    }

    @Test
    fun getPatientTest() {
        val id: Long = 1L
        val response = controller.getPatient(id)
        Assertions.assertEquals(200, response.statusCode.value())
    }


}
