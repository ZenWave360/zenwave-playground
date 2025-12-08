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
        val hisNumber: String = ""
val phoneNumber: String = ""
        val response = controller.loadPatient(hisNumber, phoneNumber)
        Assertions.assertEquals(200, response.statusCode.value())
    }

    @Test
    fun partialPatientUpdateTest() {
        val hisNumber: String = ""
val phoneNumber: String = ""
val input: Map<String, Any?> = mutableMapOf()
        val response = controller.partialPatientUpdate(hisNumber, phoneNumber, input)
        Assertions.assertEquals(200, response.statusCode.value())
    }

    @Test
    fun createPatientTest() {
        val reqBody: PatientDTO = PatientDTO(hospitalId = 1L, phoneNumber = "aaa", hisNumber = "aaa", email = "aaa")
        val response = controller.createPatient(reqBody)
        Assertions.assertEquals(201, response.statusCode.value())
    }

    @Test
    fun updatePatientTest() {
        val id: Long = 0L
val reqBody: PatientDTO = PatientDTO(hospitalId = 1L, phoneNumber = "aaa", hisNumber = "aaa", email = "aaa")
        val response = controller.updatePatient(id, reqBody)
        Assertions.assertEquals(200, response.statusCode.value())
    }

    @Test
    fun getPatientTest() {
        val id: Long = 0L
        val response = controller.getPatient(id)
        Assertions.assertEquals(200, response.statusCode.value())
    }


}
