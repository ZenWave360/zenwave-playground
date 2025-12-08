package io.zenwave360.example.clinicaltool.modules.clinical.core.outbound.jpa

import io.zenwave360.example.clinicaltool.modules.clinical.core.domain.Patient
import org.springframework.data.jpa.repository.EntityGraph
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.Optional

/** Spring Data JPA repository for the Patient entity. */
@Suppress("unused")
@Repository
interface PatientRepository : JpaRepository<Patient, Long> {

    @EntityGraph(attributePaths = ["medicalContacts", "personalContacts", "patientAddresses", "hospitalAddresses", "patientWearables"])
    override fun findById(id: Long): Optional<Patient>

    @EntityGraph(attributePaths = ["medicalContacts", "personalContacts", "patientAddresses", "hospitalAddresses", "patientWearables"])
    fun findByIdOrNull(id: Long): Patient? = findById(id).orElse(null)

    @EntityGraph(attributePaths = ["medicalContacts", "personalContacts", "patientAddresses", "hospitalAddresses", "patientWearables"])
    fun findByPhoneNumberAndHisNumber(phoneNumber: String, hisNumber: String): Patient?
}
