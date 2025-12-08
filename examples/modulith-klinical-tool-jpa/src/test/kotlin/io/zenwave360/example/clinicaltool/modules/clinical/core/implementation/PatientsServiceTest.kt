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
        val input: Patient = Patient() // TODO
        // TODO fill input data
        // input.userId = 0L
        // input.hospitalId = 0L
        // input.profilePictureId = 0L
        // input.phoneNumber = ""
        // input.hisNumber = ""
        // input.email = ""
        // input.generalInfo = GeneralInfo()
        // input.healthInsuranceInfo = HealthInsuranceInfo()
        // input.documentIds = List.of(0L)
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
        val input: Patient = Patient() // TODO
        // TODO fill input data
        // input.userId = 0L
        // input.hospitalId = 0L
        // input.profilePictureId = 0L
        // input.phoneNumber = ""
        // input.hisNumber = ""
        // input.email = ""
        // input.generalInfo = GeneralInfo()
        // input.healthInsuranceInfo = HealthInsuranceInfo()
        // input.documentIds = List.of(0L)
        // assertTrue(patientRepository.containsKey(id))
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
