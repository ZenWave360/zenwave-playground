package io.zenwave360.example.clinicaltool.modules.termsandconditions

import io.zenwave360.example.clinicaltool.modules.termsandconditions.*
import io.zenwave360.example.clinicaltool.modules.termsandconditions.config.*
import io.zenwave360.example.clinicaltool.modules.termsandconditions.domain.*
import io.zenwave360.example.clinicaltool.modules.termsandconditions.dtos.*
import io.zenwave360.example.clinicaltool.modules.termsandconditions.inmemory.*
import io.zenwave360.example.clinicaltool.modules.termsandconditions.mappers.*
import java.time.*
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mockito.*
import org.slf4j.Logger
import org.slf4j.LoggerFactory

/** Acceptance Test for TermsAndConditionsService. */
class TermsAndConditionsServiceTest {

    private val log: Logger = LoggerFactory.getLogger(javaClass)

    val context = ServicesInMemoryConfig()
    val termsAndConditionsService: TermsAndConditionsService = context.termsAndConditionsService()

    val acceptedTermsAndConditionsRepository: AcceptedTermsAndConditionsRepositoryInMemory =
        context.acceptedTermsAndConditionsRepository()

    val termsAndConditionsRepository: TermsAndConditionsRepositoryInMemory = context.termsAndConditionsRepository()

    @BeforeEach
    fun setUp() {
        context.reloadTestData()
    }

    @Test
    fun listTermsAndConditionsTest() { // TODO: implement this test
        // val results = termsAndConditionsService.listTermsAndConditions(PageRequest.of(0, 10))
        // assertNotNull(results)
    }

    @Test
    fun getTermsAndConditionsTest() { // TODO: implement this test
        val id: Long = 1L
        val termsAndConditions = termsAndConditionsService.getTermsAndConditions(id)
        assertNotNull(termsAndConditions)
    }

    @Test
    fun createTermsAndConditionsTest() { // TODO: implement this test
        val input: TermsAndConditions = TermsAndConditions() // TODO
        // TODO fill input data
        // input.content = ""
        // input.lang = ""
        // input.contentVersion = ""
        // input.startDate = LocalDate.now()
        val termsAndConditions = termsAndConditionsService.createTermsAndConditions(input)
        assertNotNull(termsAndConditions.id)
        assertTrue(termsAndConditionsRepository.containsEntity(termsAndConditions))
    }

    @Test
    fun updateTermsAndConditionsTest() { // TODO: implement this test
        val id: Long = 1L
        val input: TermsAndConditions = TermsAndConditions() // TODO
        // TODO fill input data
        // input.content = ""
        // input.lang = ""
        // input.contentVersion = ""
        // input.startDate = LocalDate.now()
        // assertTrue(termsAndConditionsRepository.containsKey(id))
        val termsAndConditions = termsAndConditionsService.updateTermsAndConditions(id, input)
        assertNotNull(termsAndConditions)
        assertTrue(termsAndConditionsRepository.containsEntity(termsAndConditions!!))
    }

    @Test
    fun getCurrentTermsAndConditionsTest() { // TODO: implement this test// TODO: implement this test
    }

    @Test
    fun acceptTermsAndConditionsTest() { // TODO: implement this test// TODO: implement this test
    }
}
