package io.zenwave360.example.clinicaltool.modules.termsandconditions

import io.zenwave360.example.clinicaltool.modules.termsandconditions.config.*
import io.zenwave360.example.clinicaltool.modules.termsandconditions.domain.*
import io.zenwave360.example.clinicaltool.modules.termsandconditions.*
import io.zenwave360.example.clinicaltool.modules.termsandconditions.dtos.*
import io.zenwave360.example.clinicaltool.modules.termsandconditions.mappers.*
import io.zenwave360.example.clinicaltool.modules.termsandconditions.*
import io.zenwave360.example.clinicaltool.modules.termsandconditions.inmemory.*

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
 * Acceptance Test for TermsAndConditionsService.
 */
class TermsAndConditionsServiceTest {

    private val log: Logger = LoggerFactory.getLogger(javaClass)

    val context = ServicesInMemoryConfig()
    val termsAndConditionsService: TermsAndConditionsService = context.termsAndConditionsService()

    val acceptedTermsAndConditionsRepository: AcceptedTermsAndConditionsRepositoryInMemory = context.acceptedTermsAndConditionsRepository()

    val termsAndConditionsRepository: TermsAndConditionsRepositoryInMemory = context.termsAndConditionsRepository()


    @BeforeEach
    fun setUp() {
        context.reloadTestData()
    }


    @Test
    fun listTermsAndConditionsTest() {// TODO: implement this test
        // val results = termsAndConditionsService.listTermsAndConditions(PageRequest.of(0, 10))
        // assertNotNull(results)
}

    @Test
    fun getTermsAndConditionsTest() {// TODO: implement this test
        val id: Long = 1L
        val termsAndConditions = termsAndConditionsService.getTermsAndConditions(id)
        assertTrue(termsAndConditions.isPresent)
}

    @Test
    fun createTermsAndConditionsTest() {// TODO: implement this test
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
    fun updateTermsAndConditionsTest() {// TODO: implement this test
        val id: Long = 1L
        val input: TermsAndConditions = TermsAndConditions() // TODO
        // TODO fill input data
        // input.content = ""
        // input.lang = ""
        // input.contentVersion = ""
        // input.startDate = LocalDate.now()
        // assertTrue(termsAndConditionsRepository.containsKey(id))
        val termsAndConditions = termsAndConditionsService.updateTermsAndConditions(id, input)
        assertTrue(termsAndConditions.isPresent)
        assertTrue(termsAndConditionsRepository.containsEntity(termsAndConditions.get()))
}

    @Test
    fun getCurrentTermsAndConditionsTest() {// TODO: implement this test// TODO: implement this test
}

    @Test
    fun acceptTermsAndConditionsTest() {// TODO: implement this test// TODO: implement this test
}

}
