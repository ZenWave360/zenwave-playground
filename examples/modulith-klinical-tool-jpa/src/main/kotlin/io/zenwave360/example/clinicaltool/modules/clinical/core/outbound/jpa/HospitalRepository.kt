package io.zenwave360.example.clinicaltool.modules.clinical.core.outbound.jpa

import io.zenwave360.example.clinicaltool.modules.clinical.core.domain.*
import java.math.*
import java.time.*
import java.util.*
import org.springframework.data.jpa.repository.*
import org.springframework.stereotype.Repository

/** Spring Data JPA repository for the Hospital entity. */
@Suppress("unused") @Repository interface HospitalRepository : JpaRepository<Hospital, Long> {}
