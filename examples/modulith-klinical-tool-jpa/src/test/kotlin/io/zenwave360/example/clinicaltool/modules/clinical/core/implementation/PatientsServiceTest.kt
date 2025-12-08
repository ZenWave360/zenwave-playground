package io.zenwave360.example.clinicaltool.modules.clinical.core.implementation

import io.zenwave360.example.clinicaltool.modules.clinical.config.*
import io.zenwave360.example.clinicaltool.modules.clinical.core.domain.*
import io.zenwave360.example.clinicaltool.modules.clinical.core.implementation.mappers.*
import io.zenwave360.example.clinicaltool.modules.clinical.core.inbound.*
import io.zenwave360.example.clinicaltool.modules.clinical.core.inbound.dtos.*
import io.zenwave360.example.clinicaltool.modules.clinical.core.outbound.jpa.*
import io.zenwave360.example.clinicaltool.modules.clinical.infrastructure.jpa.inmemory.*
import java.time.*
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mockito.*
import org.slf4j.Logger
import org.slf4j.LoggerFactory

/** Acceptance Test for PatientsService. */
class PatientsServiceTest {

    private val log: Logger = LoggerFactory.getLogger(javaClass)

    val context = ServicesInMemoryConfig()
    val patientsService: PatientsService = context.patientsService()

    val patientRepository: PatientRepositoryInMemory = context.patientRepository()

    @BeforeEach
    fun setUp() {
        context.reloadTestData()
    }

    @Test
    fun loadPatientTest() { // TODO: implement this test
    }

    @Test
    fun createPatientTest() {
        val input: Patient = Patient()
        input.userId = 1L
        input.hospitalId = 1L
        input.profilePictureId = 1L
        input.phoneNumber = "1234567890"
        input.hisNumber = "HIS123456"
        input.email = "patient@example.com"

        val generalInfo = GeneralInfo()
        generalInfo.name = "John"
        generalInfo.surname = "Doe"
        generalInfo.surname2 = "Smith"
        generalInfo.identityDocumentType = IdentityDocumentType.DNI
        generalInfo.identityDocumentNumber = "12345678A"
        generalInfo.birthDate = LocalDate.of(1990, 1, 1)
        generalInfo.gender = GenderType.MALE
        generalInfo.lang = "en"
        input.generalInfo = generalInfo

        val healthInsuranceInfo = HealthInsuranceInfo()
        healthInsuranceInfo.insuranceCompanyId = "INS123"
        healthInsuranceInfo.insuranceCardNumber = "CARD123456"
        input.healthInsuranceInfo = healthInsuranceInfo

        input.documentIds = mutableListOf(1L, 2L, 3L)
        val patient = patientsService.createPatient(input)
        assertNotNull(patient.id)
        assertTrue(patientRepository.containsEntity(patient))
    }

    @Test
    fun partialPatientUpdateTest() { // TODO: implement this test
    }

    @Test
    fun updatePatientTest() {
        val id: Long = 1L
        val input: Patient = Patient()
        input.userId = 1L
        input.hospitalId = 1L
        input.profilePictureId = 1L
        input.phoneNumber = "0987654321"
        input.hisNumber = "HIS654321"
        input.email = "updated.patient@example.com"

        val generalInfo = GeneralInfo()
        generalInfo.name = "Jane"
        generalInfo.surname = "Doe"
        generalInfo.surname2 = "Johnson"
        generalInfo.identityDocumentType = IdentityDocumentType.PASSPORT
        generalInfo.identityDocumentNumber = "P87654321"
        generalInfo.birthDate = LocalDate.of(1985, 5, 15)
        generalInfo.gender = GenderType.FEMALE
        generalInfo.lang = "es"
        input.generalInfo = generalInfo

        val healthInsuranceInfo = HealthInsuranceInfo()
        healthInsuranceInfo.insuranceCompanyId = "INS456"
        healthInsuranceInfo.insuranceCardNumber = "CARD654321"
        input.healthInsuranceInfo = healthInsuranceInfo

        input.documentIds = mutableListOf(4L, 5L)
        val patient = patientsService.updatePatient(id, input)
        assertNotNull(patient)
        assertTrue(patientRepository.containsEntity(patient!!))
    }

    @Test
    fun getPatientTest() {
        val id: Long = 1L
        val patient = patientsService.getPatient(id)
        assertNotNull(patient)
    }

    @Test
    fun getPatientProfileByIdTest() { // TODO: implement this test
    }

    @Test
    fun associateDocumentWithPatientTest() { // TODO: implement this test
    }
}
