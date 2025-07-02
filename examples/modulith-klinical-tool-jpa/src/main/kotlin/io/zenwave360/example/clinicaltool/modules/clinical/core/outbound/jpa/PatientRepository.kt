package io.zenwave360.example.clinicaltool.modules.clinical.core.outbound.jpa

import io.zenwave360.example.clinicaltool.modules.clinical.core.domain.*
import java.math.*
import java.time.*
import java.util.*

import org.springframework.data.jpa.repository.*
import org.springframework.stereotype.Repository

/**
 * Spring Data JPA repository for the Patient entity.
 */
@Suppress("unused")
@Repository
interface PatientRepository : JpaRepository<Patient, Long> {fun findByPhoneNumberAndHisNumber(phoneNumber: String, hisNumber: String): java.util.Optional<Patient>
}
