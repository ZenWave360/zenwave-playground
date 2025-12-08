package io.zenwave360.example.clinicaltool.modules.termsandconditions

import io.zenwave360.example.clinicaltool.modules.termsandconditions.*
import io.zenwave360.example.clinicaltool.modules.termsandconditions.dtos.*
import io.zenwave360.example.clinicaltool.modules.termsandconditions.config.ServicesInMemoryConfig

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.slf4j.Logger
import org.slf4j.LoggerFactory

import java.math.*
import java.time.*
import java.util.*

/**
 * Test controller for TermsAndConditionsApiController.
 */
class TermsAndConditionsApiControllerTest {

    private val log: Logger = LoggerFactory.getLogger(javaClass)

    private val context = ServicesInMemoryConfig()

    private val controller = TermsAndConditionsApiController( context.termsAndConditionsService() )

    @BeforeEach
    fun setUp() {
        context.reloadTestData()
    }


    @Test
    fun listTermsAndConditionsTest() {
        
        val response = controller.listTermsAndConditions()
        Assertions.assertEquals(200, response.statusCode.value())
    }

    @Test
    fun createTermsAndConditionsTest() {
        val reqBody: TermsAndConditionsDTO = TermsAndConditionsDTO(content = "aaa", lang = "aaa", contentVersion = "aaa", startDate = LocalDate.now())
        val response = controller.createTermsAndConditions(reqBody)
        Assertions.assertEquals(201, response.statusCode.value())
    }

    @Test
    fun getTermsAndConditionsTest() {
        val id: Long = 0L
        val response = controller.getTermsAndConditions(id)
        Assertions.assertEquals(200, response.statusCode.value())
    }

    @Test
    fun updateTermsAndConditionsTest() {
        val id: Long = 0L
val reqBody: TermsAndConditionsDTO = TermsAndConditionsDTO(content = "aaa", lang = "aaa", contentVersion = "aaa", startDate = LocalDate.now())
        val response = controller.updateTermsAndConditions(id, reqBody)
        Assertions.assertEquals(200, response.statusCode.value())
    }

    @Test
    fun getCurrentTermsAndConditionsTest() {
        val lang: String = ""
        val response = controller.getCurrentTermsAndConditions(lang)
        Assertions.assertEquals(200, response.statusCode.value())
    }

    @Test
    fun acceptTermsAndConditionsTest() {
        val reqBody: AcceptedTermsAndConditionsInputDTO = AcceptedTermsAndConditionsInputDTO(userId = 1L, termsAndConditionsId = 1L)
        val response = controller.acceptTermsAndConditions(reqBody)
        Assertions.assertEquals(200, response.statusCode.value())
    }


}
