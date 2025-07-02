package io.zenwave360.example.clinicaltool.modules.clinical.infrastructure.jpa

import io.zenwave360.example.clinicaltool.common.BaseRepositoryIntegrationTest
import io.zenwave360.example.clinicaltool.modules.clinical.core.domain.*
import io.zenwave360.example.clinicaltool.modules.clinical.core.outbound.jpa.ProvisionalPatientRepository

import java.util.HashSet
import java.util.HashMap
import java.util.List
import java.time.*
import java.math.BigDecimal

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired

import jakarta.persistence.EntityManager

class ProvisionalPatientRepositoryIntegrationTest : BaseRepositoryIntegrationTest() {

    @Autowired
    lateinit var entityManager: EntityManager

    @Autowired
    lateinit var provisionalPatientRepository: ProvisionalPatientRepository

    @Test
    fun findAllTest() {
        val results = provisionalPatientRepository.findAll()
        Assertions.assertFalse(results.isEmpty())
    }

    @Test
    fun findByIdTest() {
        val id = 1L
        val provisionalPatient = provisionalPatientRepository.findById(id).orElseThrow()
        Assertions.assertNotNull(provisionalPatient.id)
        Assertions.assertNotNull(provisionalPatient.version)
    }

    @Test
    fun saveTest() {
        val provisionalPatient = ProvisionalPatient()
        provisionalPatient.phoneNumber = ""
        provisionalPatient.hisNumber = ""
        provisionalPatient.patient = Patient()



        // Persist aggregate root
        val created = provisionalPatientRepository.save(provisionalPatient)

        // reloading to get relationships persisted by id
        entityManager.flush()
        entityManager.refresh(created)
        Assertions.assertNotNull(created.id)
        Assertions.assertNotNull(created.version)


    }

    @Test
    fun updateTest() {
        val id = 1L
        val provisionalPatient = provisionalPatientRepository.findById(id).orElseThrow()
        provisionalPatient.phoneNumber = ""
        provisionalPatient.hisNumber = ""
        provisionalPatient.patient = Patient()

        val updated = provisionalPatientRepository.save(provisionalPatient)
        Assertions.assertEquals("", updated.phoneNumber)
        Assertions.assertEquals("", updated.hisNumber)
        Assertions.assertEquals(Patient(), updated.patient)
    }

    @Test
    fun deleteTest() {
        val id = 1L
        provisionalPatientRepository.deleteById(id)
        val notFound = provisionalPatientRepository.findById(id)
        Assertions.assertFalse(notFound.isPresent)
    }
}