package io.zenwave360.example.clinicaltool.modules.termsandconditions

import io.zenwave360.example.clinicaltool.common.BaseRepositoryIntegrationTest
import io.zenwave360.example.clinicaltool.modules.termsandconditions.domain.*
import io.zenwave360.example.clinicaltool.modules.termsandconditions.AcceptedTermsAndConditionsRepository

import java.util.HashSet
import java.util.HashMap
import java.util.List
import java.time.*
import java.math.BigDecimal

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired

import jakarta.persistence.EntityManager

class AcceptedTermsAndConditionsRepositoryIntegrationTest : BaseRepositoryIntegrationTest() {

    @Autowired
    lateinit var entityManager: EntityManager

    @Autowired
    lateinit var acceptedTermsAndConditionsRepository: AcceptedTermsAndConditionsRepository

    @Test
    fun findAllTest() {
        val results = acceptedTermsAndConditionsRepository.findAll()
        Assertions.assertFalse(results.isEmpty())
    }

    @Test
    fun findByIdTest() {
        val id = 1L
        val acceptedTermsAndConditions = acceptedTermsAndConditionsRepository.findById(id).orElseThrow()
        Assertions.assertNotNull(acceptedTermsAndConditions.id)
        Assertions.assertNotNull(acceptedTermsAndConditions.version)
    }

    @Test
    fun saveTest() {
        val acceptedTermsAndConditions = AcceptedTermsAndConditions()
        acceptedTermsAndConditions.userId = 0L
        acceptedTermsAndConditions.termsAndConditionsId = 0L
        acceptedTermsAndConditions.acceptedDate = Instant.now()



        // Persist aggregate root
        val created = acceptedTermsAndConditionsRepository.save(acceptedTermsAndConditions)

        // reloading to get relationships persisted by id
        entityManager.flush()
        entityManager.refresh(created)
        Assertions.assertNotNull(created.id)
        Assertions.assertNotNull(created.version)


    }

    @Test
    fun updateTest() {
        val id = 1L
        val acceptedTermsAndConditions = acceptedTermsAndConditionsRepository.findById(id).orElseThrow()
        acceptedTermsAndConditions.userId = 0L
        acceptedTermsAndConditions.termsAndConditionsId = 0L
        acceptedTermsAndConditions.acceptedDate = Instant.now()

        val updated = acceptedTermsAndConditionsRepository.save(acceptedTermsAndConditions)
        Assertions.assertEquals(0L, updated.userId)
        Assertions.assertEquals(0L, updated.termsAndConditionsId)
        Assertions.assertEquals(Instant.now(), updated.acceptedDate)
    }

    @Test
    fun deleteTest() {
        val id = 1L
        acceptedTermsAndConditionsRepository.deleteById(id)
        val notFound = acceptedTermsAndConditionsRepository.findById(id)
        Assertions.assertFalse(notFound.isPresent)
    }
}