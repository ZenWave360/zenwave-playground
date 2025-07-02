package io.zenwave360.example.clinicaltool.modules.clinical.infrastructure.jpa

import io.zenwave360.example.clinicaltool.common.BaseRepositoryIntegrationTest
import io.zenwave360.example.clinicaltool.modules.clinical.core.domain.*
import io.zenwave360.example.clinicaltool.modules.clinical.core.outbound.jpa.DoctorRepository

import java.util.HashSet
import java.util.HashMap
import java.util.List
import java.time.*
import java.math.BigDecimal

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired

import jakarta.persistence.EntityManager

class DoctorRepositoryIntegrationTest : BaseRepositoryIntegrationTest() {

    @Autowired
    lateinit var entityManager: EntityManager

    @Autowired
    lateinit var doctorRepository: DoctorRepository

    @Test
    fun findAllTest() {
        val results = doctorRepository.findAll()
        Assertions.assertFalse(results.isEmpty())
    }

    @Test
    fun findByIdTest() {
        val id = 1L
        val doctor = doctorRepository.findById(id).orElseThrow()
        Assertions.assertNotNull(doctor.id)
        Assertions.assertNotNull(doctor.version)
        Assertions.assertNotNull(doctor.createdBy)
        Assertions.assertNotNull(doctor.createdDate)
    }

    @Test
    fun saveTest() {
        val doctor = Doctor()
        doctor.userId = 0L
        doctor.profilePictureId = 0L
        doctor.hospitalId = 0L
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
        val doctor = doctorRepository.findById(id).orElseThrow()
        doctor.userId = 0L
        doctor.profilePictureId = 0L
        doctor.hospitalId = 0L
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
        val notFound = doctorRepository.findById(id)
        Assertions.assertFalse(notFound.isPresent)
    }
}