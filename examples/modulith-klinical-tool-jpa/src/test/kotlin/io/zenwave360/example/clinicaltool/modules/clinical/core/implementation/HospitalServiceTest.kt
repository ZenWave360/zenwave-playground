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

/** Acceptance Test for HospitalService. */
class HospitalServiceTest {

    private val log: Logger = LoggerFactory.getLogger(javaClass)

    val context = ServicesInMemoryConfig()
    val hospitalService: HospitalService = context.hospitalService()

    val hospitalRepository: HospitalRepositoryInMemory = context.hospitalRepository()

    val doctorRepository: DoctorRepositoryInMemory = context.doctorRepository()

    @BeforeEach
    fun setUp() {
        context.reloadTestData()
    }

    @Test
    fun getHospitalTest() {
        val id: Long = 1L
        val hospital = hospitalService.getHospital(id)
        assertNotNull(hospital)
    }

    @Test
    fun createHospitalTest() {
        val input: Hospital = Hospital()
        input.name = "Test Hospital"
        input.lang = "en"
        input.timezone = "UTC"
        val hospital = hospitalService.createHospital(input)
        assertNotNull(hospital.id)
        assertTrue(hospitalRepository.containsEntity(hospital))
    }

    @Test
    fun updateHospitalTest() {
        val id: Long = 1L
        val input: Hospital = Hospital()
        input.name = "Updated Hospital"
        input.lang = "es"
        input.timezone = "ECT"
        val hospital = hospitalService.updateHospital(id, input)
        assertNotNull(hospital)
        assertTrue(hospitalRepository.containsEntity(hospital!!))
    }

    @Test
    fun listHospitalsTest() {
        // val results = hospitalService.listHospitals(PageRequest.of(0, 10))
        // assertNotNull(results)// TODO: implement this test
    }

    @Test
    fun createDoctorTest() {
        val input: Doctor = Doctor()
        input.userId = 1L
        input.profilePictureId = 1L
        input.hospitalId = 1L
        input.name = "John"
        input.surname = "Doe"
        input.surname2 = "Smith"
        input.email = "john.doe@example.com"
        input.phoneNumber = "1234567890"
        input.lang = "en"
        val doctor = hospitalService.createDoctor(input)
        assertNotNull(doctor.id)
        assertTrue(doctorRepository.containsEntity(doctor))
    }

    @Test
    fun updateDoctorTest() {
        val id: Long = 1L
        val input: Doctor = Doctor()
        input.userId = 1L
        input.profilePictureId = 1L
        input.hospitalId = 1L
        input.name = "Jane"
        input.surname = "Doe"
        input.surname2 = "Johnson"
        input.email = "jane.doe@example.com"
        input.phoneNumber = "0987654321"
        input.lang = "es"
        val doctor = hospitalService.updateDoctor(id, input)
        assertNotNull(doctor)
        assertTrue(doctorRepository.containsEntity(doctor!!))
    }

    @Test
    fun getDoctorTest() {
        val id: Long = 1L
        val doctor = hospitalService.getDoctor(id)
        assertNotNull(doctor)
    }

    @Test
    fun listDoctorsTest() { // TODO: implement this test
        // val results = hospitalService.listDoctors(PageRequest.of(0, 10))
        // assertNotNull(results)
    }

    @Test
    fun listHospitalDoctorsTest() { // TODO: implement this test// TODO: implement this test
    }

    @Test
    fun listHospitalPatientsTest() { // TODO: implement this test// TODO: implement this test
    }
}
