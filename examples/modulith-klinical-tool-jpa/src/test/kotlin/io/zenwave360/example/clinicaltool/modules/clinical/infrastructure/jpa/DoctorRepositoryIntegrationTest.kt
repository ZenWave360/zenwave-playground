package io.zenwave360.example.clinicaltool.modules.clinical.infrastructure.jpa

import io.zenwave360.example.clinicaltool.common.BaseRepositoryIntegrationTest
import io.zenwave360.example.clinicaltool.modules.clinical.core.domain.*
import io.zenwave360.example.clinicaltool.modules.clinical.core.outbound.jpa.DoctorRepository
import jakarta.persistence.EntityManager
import java.time.*
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.findByIdOrNull

class DoctorRepositoryIntegrationTest : BaseRepositoryIntegrationTest() {

    @Autowired lateinit var entityManager: EntityManager

    @Autowired lateinit var doctorRepository: DoctorRepository

    @Test
    fun findAllTest() {
        val results = doctorRepository.findAll()
        Assertions.assertFalse(results.isEmpty())
    }

    @Test
    fun findByIdTest() {
        val id = 1L
        val doctor = doctorRepository.findByIdOrNull(id) ?: throw NoSuchElementException(" not found with id: $id")
        Assertions.assertNotNull(doctor.id)
        Assertions.assertNotNull(doctor.version)
        Assertions.assertNotNull(doctor.createdBy)
        Assertions.assertNotNull(doctor.createdDate)
    }

    @Test
    fun saveTest() {
        val doctor = Doctor()
        doctor.userId = 1L
        doctor.profilePictureId = 1L
        doctor.hospitalId = 1L
        doctor.name = ""
        doctor.surname = ""
        doctor.surname2 = ""
        doctor.email = ""
        doctor.phoneNumber = ""
        doctor.lang = ""

        // Persist aggregate root
        val created = doctorRepository.save(doctor)

        // reloading to get relationships persisted by id
        entityManager.flush()
        entityManager.refresh(created)
        Assertions.assertNotNull(created.id)
        Assertions.assertNotNull(created.version)
        Assertions.assertNotNull(created.createdBy)
        Assertions.assertNotNull(created.createdDate)
    }

    @Test
    fun updateTest() {
        val id = 1L
        val doctor = doctorRepository.findByIdOrNull(id) ?: throw NoSuchElementException(" not found with id: $id")
        doctor.userId = 1L
        doctor.profilePictureId = 1L
        doctor.hospitalId = 1L
        doctor.name = ""
        doctor.surname = ""
        doctor.surname2 = ""
        doctor.email = ""
        doctor.phoneNumber = ""
        doctor.lang = ""

        val updated = doctorRepository.save(doctor)
        Assertions.assertEquals(0L, updated.userId)
        Assertions.assertEquals(0L, updated.profilePictureId)
        Assertions.assertEquals(0L, updated.hospitalId)
        Assertions.assertEquals("", updated.name)
        Assertions.assertEquals("", updated.surname)
        Assertions.assertEquals("", updated.surname2)
        Assertions.assertEquals("", updated.email)
        Assertions.assertEquals("", updated.phoneNumber)
        Assertions.assertEquals("", updated.lang)
    }

    @Test
    fun deleteTest() {
        val id = 1L
        doctorRepository.deleteById(id)
        val notFound = doctorRepository.findByIdOrNull(id)
        Assertions.assertNull(notFound)
    }
}
