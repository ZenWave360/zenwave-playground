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
 * Test controller for HospitalApiController.
 */
class HospitalApiControllerTest {

    private val log: Logger = LoggerFactory.getLogger(javaClass)

    private val context = ServicesInMemoryConfig()

    private val controller = HospitalApiController( context.hospitalService() )

    @BeforeEach
    fun setUp() {
        context.reloadTestData()
    }


    @Test
    fun getHospitalTest() {
        val id: Long = 1L
        val response = controller.getHospital(id)
        Assertions.assertEquals(200, response.statusCode.value())
        Assertions.assertNotNull(response.body)
    }

    @Test
    fun updateHospitalTest() {
        val id: Long = 1L
        val reqBody = HospitalDTO(
            name = "Updated Hospital",
            lang = "es",
            timezone = "ECT"
        )
        val response = controller.updateHospital(id, reqBody)
        Assertions.assertEquals(200, response.statusCode.value())
        Assertions.assertNotNull(response.body)
    }

    @Test
    fun createHospitalTest() {
        val reqBody = HospitalDTO(
            name = "Test Hospital",
            lang = "en",
            timezone = "UTC"
        )
        val response = controller.createHospital(reqBody)
        Assertions.assertEquals(201, response.statusCode.value())
    }

    @Test
    fun listHospitalsTest() {

        val response = controller.listHospitals()
        Assertions.assertEquals(200, response.statusCode.value())
    }

    @Test
    fun createDoctorTest() {
        val reqBody = DoctorDTO(
            hospitalId = 1L,
            name = "John",
            surname = "Doe",
            email = "john.doe@example.com"
        )
        val response = controller.createDoctor(reqBody)
        Assertions.assertEquals(201, response.statusCode.value())
    }

    @Test
    fun listDoctorsTest() {

        val response = controller.listDoctors()
        Assertions.assertEquals(200, response.statusCode.value())
    }

    @Test
    fun updateDoctorTest() {
        val id: Long = 1L
        val reqBody = DoctorDTO(
            hospitalId = 1L,
            name = "Jane",
            surname = "Doe",
            email = "jane.doe@example.com"
        )
        val response = controller.updateDoctor(id, reqBody)
        Assertions.assertEquals(200, response.statusCode.value())
        Assertions.assertNotNull(response.body)
    }

    @Test
    fun getDoctorTest() {
        val id: Long = 1L
        val response = controller.getDoctor(id)
        Assertions.assertEquals(200, response.statusCode.value())
        Assertions.assertNotNull(response.body)
    }

    @Test
    fun listHospitalDoctorsTest() {
        val hospitalId: Long = 1L
        val response = controller.listHospitalDoctors(hospitalId)
        Assertions.assertEquals(200, response.statusCode.value())
    }

    @Test
    fun listHospitalPatientsTest() {
        val hospitalId: Long = 1L
        val response = controller.listHospitalPatients(hospitalId)
        Assertions.assertEquals(200, response.statusCode.value())
    }


}
