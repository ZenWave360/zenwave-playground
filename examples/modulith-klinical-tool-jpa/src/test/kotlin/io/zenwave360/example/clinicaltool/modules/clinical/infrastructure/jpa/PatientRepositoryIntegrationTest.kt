package io.zenwave360.example.clinicaltool.modules.clinical.infrastructure.jpa

import io.zenwave360.example.clinicaltool.common.BaseRepositoryIntegrationTest
import io.zenwave360.example.clinicaltool.modules.clinical.core.domain.*
import io.zenwave360.example.clinicaltool.modules.clinical.core.outbound.jpa.PatientRepository
import jakarta.persistence.EntityManager
import java.time.*
import java.util.HashSet
import java.util.List
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.findByIdOrNull

class PatientRepositoryIntegrationTest : BaseRepositoryIntegrationTest() {

    @Autowired lateinit var entityManager: EntityManager

    @Autowired lateinit var patientRepository: PatientRepository

    @Test
    fun findAllTest() {
        val results = patientRepository.findAll()
        Assertions.assertFalse(results.isEmpty())
    }

    @Test
    fun findByIdTest() {
        val id = 1L
        val patient = patientRepository.findByIdOrNull(id) ?: throw NoSuchElementException(" not found with id: $id")
        Assertions.assertNotNull(patient.id)
        Assertions.assertNotNull(patient.version)
        Assertions.assertNotNull(patient.createdBy)
        Assertions.assertNotNull(patient.createdDate)
    }

    @Test
    fun saveTest() {
        val patient = Patient()
        patient.userId = 0L
        patient.hospitalId = 0L
        patient.profilePictureId = 0L
        patient.phoneNumber = ""
        patient.hisNumber = ""
        patient.email = ""
        patient.generalInfo = GeneralInfo()
        patient.healthInsuranceInfo = HealthInsuranceInfo()
        patient.documentIds = List.of(0L)

        // OneToMany medicalContacts owner: true
        val medicalContacts = MedicalContact()
        medicalContacts.name = ""
        medicalContacts.surname = ""
        medicalContacts.surname2 = ""
        medicalContacts.hospital = ""
        medicalContacts.area = ""
        medicalContacts.jobPosition = ""
        medicalContacts.phoneNumber = ""
        medicalContacts.email = ""
        patient.medicalContacts = HashSet()
        patient.addMedicalContacts(medicalContacts)

        // OneToMany personalContacts owner: true
        val personalContacts = PersonalContact()
        personalContacts.name = ""
        personalContacts.surname = ""
        personalContacts.surname2 = ""
        personalContacts.phoneNumber = ""
        personalContacts.email = ""
        personalContacts.patientRelationshipType = PatientRelationshipType.values()[0]
        personalContacts.emergencyContact = false
        patient.personalContacts = HashSet()
        patient.addPersonalContacts(personalContacts)

        // OneToMany patientAddresses owner: true
        val patientAddresses = PatientAddress()
        patientAddresses.street = ""
        patientAddresses.city = ""
        patientAddresses.postalCode = ""
        patientAddresses.countryCode = ""
        patientAddresses.additionalInfo = ""
        patientAddresses.current = false
        patient.patientAddresses = HashSet()
        patient.addPatientAddresses(patientAddresses)

        // OneToMany hospitalAddresses owner: true
        val hospitalAddresses = HospitalAddress()
        hospitalAddresses.street = ""
        hospitalAddresses.city = ""
        hospitalAddresses.postalCode = ""
        hospitalAddresses.countryCode = ""
        hospitalAddresses.additionalInfo = ""
        patient.hospitalAddresses = HashSet()
        patient.addHospitalAddresses(hospitalAddresses)

        // OneToMany patientWearables owner: true
        val patientWearables = PatientWearable()
        patientWearables.wearableType = ""
        patientWearables.serialNumber = ""
        patient.patientWearables = HashSet()
        patient.addPatientWearables(patientWearables)

        // Persist aggregate root
        val created = patientRepository.save(patient)

        // reloading to get relationships persisted by id
        entityManager.flush()
        entityManager.refresh(created)
        Assertions.assertNotNull(created.id)
        Assertions.assertNotNull(created.version)
        Assertions.assertNotNull(created.createdBy)
        Assertions.assertNotNull(created.createdDate)

        Assertions.assertTrue(patient.medicalContacts?.stream()?.allMatch { item -> item.id != null } == true)
        Assertions.assertTrue(patient.personalContacts?.stream()?.allMatch { item -> item.id != null } == true)
        Assertions.assertTrue(patient.patientAddresses?.stream()?.allMatch { item -> item.id != null } == true)
        Assertions.assertTrue(patient.hospitalAddresses?.stream()?.allMatch { item -> item.id != null } == true)
        Assertions.assertTrue(patient.patientWearables?.stream()?.allMatch { item -> item.id != null } == true)
    }

    @Test
    fun updateTest() {
        val id = 1L
        val patient = patientRepository.findByIdOrNull(id) ?: throw NoSuchElementException(" not found with id: $id")
        patient.userId = 0L
        patient.hospitalId = 0L
        patient.profilePictureId = 0L
        patient.phoneNumber = ""
        patient.hisNumber = ""
        patient.email = ""
        patient.generalInfo = GeneralInfo()
        patient.healthInsuranceInfo = HealthInsuranceInfo()
        patient.documentIds = List.of(0L)

        val updated = patientRepository.save(patient)
        Assertions.assertEquals(0L, updated.userId)
        Assertions.assertEquals(0L, updated.hospitalId)
        Assertions.assertEquals(0L, updated.profilePictureId)
        Assertions.assertEquals("", updated.phoneNumber)
        Assertions.assertEquals("", updated.hisNumber)
        Assertions.assertEquals("", updated.email)
        Assertions.assertEquals(GeneralInfo(), updated.generalInfo)
        Assertions.assertEquals(HealthInsuranceInfo(), updated.healthInsuranceInfo)
        Assertions.assertEquals(List.of(0L), updated.documentIds)
    }

    @Test
    fun deleteTest() {
        val id = 1L
        patientRepository.deleteById(id)
        val notFound = patientRepository.findByIdOrNull(id)
        Assertions.assertNull(notFound)
    }
}
