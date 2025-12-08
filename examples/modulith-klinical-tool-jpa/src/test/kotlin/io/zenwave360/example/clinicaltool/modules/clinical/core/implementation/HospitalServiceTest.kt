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
        assertNotNull(hospital) // TODO: implement this test
    }

    @Test
    fun createHospitalTest() {
        val input: Hospital = Hospital() // TODO
        // TODO fill input data
        // input.name = ""
        // input.lang = ""
        // input.timezone = ""
        val hospital = hospitalService.createHospital(input)
        assertNotNull(hospital.id)
        assertTrue(hospitalRepository.containsEntity(hospital)) // TODO: implement this test
    }

    @Test
    fun updateHospitalTest() {
        val id: Long = 1L
        val input: Hospital = Hospital() // TODO
        // TODO fill input data
        // input.name = ""
        // input.lang = ""
        // input.timezone = ""
        // assertTrue(hospitalRepository.containsKey(id))
        val hospital = hospitalService.updateHospital(id, input)
        assertNotNull(hospital)
        assertTrue(hospitalRepository.containsEntity(hospital!!)) // TODO: implement this test
    }

    @Test
    fun listHospitalsTest() {
        // val results = hospitalService.listHospitals(PageRequest.of(0, 10))
        // assertNotNull(results)// TODO: implement this test
    }

    @Test
    fun createDoctorTest() { // TODO: implement this test
        val input: Doctor = Doctor() // TODO
        // TODO fill input data
        // input.userId = 0L
        // input.profilePictureId = 0L
        // input.hospitalId = 0L
        // input.name = ""
        // input.surname = ""
        // input.surname2 = ""
        // input.email = ""
        // input.phoneNumber = ""
        // input.lang = ""
        val doctor = hospitalService.createDoctor(input)
        assertNotNull(doctor.id)
        assertTrue(doctorRepository.containsEntity(doctor))
    }

    @Test
    fun updateDoctorTest() { // TODO: implement this test
        val id: Long = 1L
        val input: Doctor = Doctor() // TODO
        // TODO fill input data
        // input.userId = 0L
        // input.profilePictureId = 0L
        // input.hospitalId = 0L
        // input.name = ""
        // input.surname = ""
        // input.surname2 = ""
        // input.email = ""
        // input.phoneNumber = ""
        // input.lang = ""
        // assertTrue(doctorRepository.containsKey(id))
        val doctor = hospitalService.updateDoctor(id, input)
        assertNotNull(doctor)
        assertTrue(doctorRepository.containsEntity(doctor!!))
    }

    @Test
    fun getDoctorTest() { // TODO: implement this test
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
