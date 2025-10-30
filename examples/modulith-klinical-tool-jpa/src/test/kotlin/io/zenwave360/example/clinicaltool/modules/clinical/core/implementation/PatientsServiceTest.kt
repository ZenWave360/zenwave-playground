package io.zenwave360.example.clinicaltool.modules.clinical.core.implementation

import io.zenwave360.example.clinicaltool.modules.clinical.config.*
import io.zenwave360.example.clinicaltool.modules.clinical.core.domain.*
import io.zenwave360.example.clinicaltool.modules.clinical.core.inbound.*
import io.zenwave360.example.clinicaltool.modules.clinical.core.inbound.dtos.*
import io.zenwave360.example.clinicaltool.modules.clinical.core.implementation.mappers.*
import io.zenwave360.example.clinicaltool.modules.clinical.core.outbound.jpa.*
import io.zenwave360.example.clinicaltool.modules.clinical.infrastructure.jpa.inmemory.*

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mapstruct.factory.Mappers
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.PageRequest

import java.util.Map
import java.util.Optional
import java.time.*
import java.math.BigDecimal

import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Assertions.assertFalse
import org.mockito.Mockito.*

/**
 * Acceptance Test for PatientsService.
 */
class PatientsServiceTest {

    private val log: Logger = LoggerFactory.getLogger(javaClass)

    val context = ServicesInMemoryConfig()
    val patientsService: PatientsService = context.patientsService()

    val patientRepository: PatientRepositoryInMemory = context.patientRepository()

    val provisionalPatientRepository: ProvisionalPatientRepositoryInMemory = context.provisionalPatientRepository()


    @BeforeEach
    fun setUp() {
        context.reloadTestData()
    }


    @Test
    fun loadPatientTest() {// TODO: implement this test// TODO: implement this test
}

    @Test
    fun partialPatientUpdateTest() {// TODO: implement this test// TODO: implement this test
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
        assertTrue(patientRepository.containsEntity(patient))// TODO: implement this test
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
        assertTrue(patient.isPresent)
        assertTrue(patientRepository.containsEntity(patient.get()))// TODO: implement this test
}

    @Test
    fun getPatientTest() {
        val id: Long = 1L
        val patient = patientsService.getPatient(id)
        assertTrue(patient.isPresent)// TODO: implement this test
}

    @Test
    fun getPatientProfileByIdTest() {// TODO: implement this test// TODO: implement this test
}

    @Test
    fun requestOptOutTest() {// TODO: implement this test// TODO: implement this test
}

    @Test
    fun associateDocumentWithPatientTest() {// TODO: implement this test// TODO: implement this test
}

}
