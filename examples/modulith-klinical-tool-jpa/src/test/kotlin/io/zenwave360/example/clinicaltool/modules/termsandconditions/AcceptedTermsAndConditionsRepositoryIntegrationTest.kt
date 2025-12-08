package io.zenwave360.example.clinicaltool.modules.termsandconditions

import io.zenwave360.example.clinicaltool.common.BaseRepositoryIntegrationTest
import io.zenwave360.example.clinicaltool.modules.termsandconditions.domain.*
import jakarta.persistence.EntityManager
import java.time.*
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.findByIdOrNull

class AcceptedTermsAndConditionsRepositoryIntegrationTest : BaseRepositoryIntegrationTest() {

    @Autowired lateinit var entityManager: EntityManager

    @Autowired lateinit var acceptedTermsAndConditionsRepository: AcceptedTermsAndConditionsRepository

    @Test
    fun findAllTest() {
        val results = acceptedTermsAndConditionsRepository.findAll()
        Assertions.assertFalse(results.isEmpty())
    }

    @Test
    fun findByIdTest() {
        val id = 1L
        val acceptedTermsAndConditions =
            acceptedTermsAndConditionsRepository.findByIdOrNull(id)
                ?: throw NoSuchElementException(" not found with id: $id")
        Assertions.assertNotNull(acceptedTermsAndConditions.id)
        Assertions.assertNotNull(acceptedTermsAndConditions.version)
    }

    @Test
    fun saveTest() {
        val acceptedTermsAndConditions = AcceptedTermsAndConditions()
        acceptedTermsAndConditions.userId = 1L
        acceptedTermsAndConditions.termsAndConditionsId = 1L
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
        val acceptedTermsAndConditions =
            acceptedTermsAndConditionsRepository.findByIdOrNull(id)
                ?: throw NoSuchElementException(" not found with id: $id")
        acceptedTermsAndConditions.userId = 1L
        acceptedTermsAndConditions.termsAndConditionsId = 1L
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
        val notFound = acceptedTermsAndConditionsRepository.findByIdOrNull(id)
        Assertions.assertNull(notFound)
    }
}
