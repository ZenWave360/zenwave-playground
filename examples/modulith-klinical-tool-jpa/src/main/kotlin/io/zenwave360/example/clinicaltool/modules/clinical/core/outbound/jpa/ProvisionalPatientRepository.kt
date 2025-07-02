package io.zenwave360.example.clinicaltool.modules.clinical.core.outbound.jpa

import io.zenwave360.example.clinicaltool.modules.clinical.core.domain.*
import java.math.*
import java.time.*
import java.util.*

import org.springframework.data.jpa.repository.*
import org.springframework.stereotype.Repository

/**
 * Spring Data JPA repository for the ProvisionalPatient entity.
 */
@Suppress("unused")
@Repository
interface ProvisionalPatientRepository : JpaRepository<ProvisionalPatient, Long> {fun findByPhoneNumberAndHisNumber(phoneNumber: String, hisNumber: String): java.util.Optional<ProvisionalPatient>
}
