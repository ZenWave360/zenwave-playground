package io.zenwave360.example.clinicaltool.modules.web.mobile

import io.zenwave360.example.clinicaltool.modules.web.mobile.*
import io.zenwave360.example.clinicaltool.modules.web.mobile.dtos.*
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
    fun getPatientProfileByIdTest() {
        val id: Long = 1L
        val response = controller.getPatientProfileById(id)
        Assertions.assertEquals(200, response.statusCode.value())
    }


}
