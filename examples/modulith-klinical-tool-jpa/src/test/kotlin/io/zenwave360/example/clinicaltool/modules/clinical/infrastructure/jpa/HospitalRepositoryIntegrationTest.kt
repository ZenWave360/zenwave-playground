package io.zenwave360.example.clinicaltool.modules.clinical.infrastructure.jpa

import io.zenwave360.example.clinicaltool.common.BaseRepositoryIntegrationTest
import io.zenwave360.example.clinicaltool.modules.clinical.core.domain.*
import io.zenwave360.example.clinicaltool.modules.clinical.core.outbound.jpa.HospitalRepository
import jakarta.persistence.EntityManager
import java.time.*
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.findByIdOrNull

class HospitalRepositoryIntegrationTest : BaseRepositoryIntegrationTest() {

    @Autowired lateinit var entityManager: EntityManager

    @Autowired lateinit var hospitalRepository: HospitalRepository

    @Test
    fun findAllTest() {
        val results = hospitalRepository.findAll()
        Assertions.assertFalse(results.isEmpty())
    }

    @Test
    fun findByIdTest() {
        val id = 1L
        val hospital = hospitalRepository.findByIdOrNull(id) ?: throw NoSuchElementException(" not found with id: $id")
        Assertions.assertNotNull(hospital.id)
        Assertions.assertNotNull(hospital.version)
    }

    @Test
    fun saveTest() {
        val hospital = Hospital()
        hospital.name = ""
        hospital.lang = ""
        hospital.timezone = ""

        // Persist aggregate root
        val created = hospitalRepository.save(hospital)

        // reloading to get relationships persisted by id
        entityManager.flush()
        entityManager.refresh(created)
        Assertions.assertNotNull(created.id)
        Assertions.assertNotNull(created.version)
    }

    @Test
    fun updateTest() {
        val id = 1L
        val hospital = hospitalRepository.findByIdOrNull(id) ?: throw NoSuchElementException(" not found with id: $id")
        hospital.name = ""
        hospital.lang = ""
        hospital.timezone = ""

        val updated = hospitalRepository.save(hospital)
        Assertions.assertEquals("", updated.name)
        Assertions.assertEquals("", updated.lang)
        Assertions.assertEquals("", updated.timezone)
    }

    @Test
    fun deleteTest() {
        val id = 1L
        hospitalRepository.deleteById(id)
        val notFound = hospitalRepository.findByIdOrNull(id)
        Assertions.assertNull(notFound)
    }
}
