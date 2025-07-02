package io.zenwave360.example.clinicaltool.modules.termsandconditions

import io.zenwave360.example.clinicaltool.common.BaseRepositoryIntegrationTest
import io.zenwave360.example.clinicaltool.modules.termsandconditions.domain.*
import io.zenwave360.example.clinicaltool.modules.termsandconditions.TermsAndConditionsRepository

import java.util.HashSet
import java.util.HashMap
import java.util.List
import java.time.*
import java.math.BigDecimal

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired

import jakarta.persistence.EntityManager

class TermsAndConditionsRepositoryIntegrationTest : BaseRepositoryIntegrationTest() {

    @Autowired
    lateinit var entityManager: EntityManager

    @Autowired
    lateinit var termsAndConditionsRepository: TermsAndConditionsRepository

    @Test
    fun findAllTest() {
        val results = termsAndConditionsRepository.findAll()
        Assertions.assertFalse(results.isEmpty())
    }

    @Test
    fun findByIdTest() {
        val id = 1L
        val termsAndConditions = termsAndConditionsRepository.findById(id).orElseThrow()
        Assertions.assertNotNull(termsAndConditions.id)
        Assertions.assertNotNull(termsAndConditions.version)
    }

    @Test
    fun saveTest() {
        val termsAndConditions = TermsAndConditions()
        termsAndConditions.content = ""
        termsAndConditions.lang = ""
        termsAndConditions.contentVersion = ""
        termsAndConditions.startDate = LocalDate.now()



        // Persist aggregate root
        val created = termsAndConditionsRepository.save(termsAndConditions)

        // reloading to get relationships persisted by id
        entityManager.flush()
        entityManager.refresh(created)
        Assertions.assertNotNull(created.id)
        Assertions.assertNotNull(created.version)


    }

    @Test
    fun updateTest() {
        val id = 1L
        val termsAndConditions = termsAndConditionsRepository.findById(id).orElseThrow()
        termsAndConditions.content = ""
        termsAndConditions.lang = ""
        termsAndConditions.contentVersion = ""
        termsAndConditions.startDate = LocalDate.now()

        val updated = termsAndConditionsRepository.save(termsAndConditions)
        Assertions.assertEquals("", updated.content)
        Assertions.assertEquals("", updated.lang)
        Assertions.assertEquals("", updated.contentVersion)
        Assertions.assertEquals(LocalDate.now(), updated.startDate)
    }

    @Test
    fun deleteTest() {
        val id = 1L
        termsAndConditionsRepository.deleteById(id)
        val notFound = termsAndConditionsRepository.findById(id)
        Assertions.assertFalse(notFound.isPresent)
    }
}