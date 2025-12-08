package io.zenwave360.example.clinicaltool.modules.termsandconditions

import io.zenwave360.example.clinicaltool.common.BaseRepositoryIntegrationTest
import io.zenwave360.example.clinicaltool.modules.termsandconditions.domain.*
import jakarta.persistence.EntityManager
import java.time.*
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.findByIdOrNull

class TermsAndConditionsRepositoryIntegrationTest : BaseRepositoryIntegrationTest() {

    @Autowired lateinit var entityManager: EntityManager

    @Autowired lateinit var termsAndConditionsRepository: TermsAndConditionsRepository

    @Test
    fun findAllTest() {
        val results = termsAndConditionsRepository.findAll()
        Assertions.assertFalse(results.isEmpty())
    }

    @Test
    fun findByIdTest() {
        val id = 1L
        val termsAndConditions =
            termsAndConditionsRepository.findByIdOrNull(id) ?: throw NoSuchElementException(" not found with id: $id")
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
        val termsAndConditions =
            termsAndConditionsRepository.findByIdOrNull(id) ?: throw NoSuchElementException(" not found with id: $id")
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
        val notFound = termsAndConditionsRepository.findByIdOrNull(id)
        Assertions.assertNull(notFound)
    }
}
