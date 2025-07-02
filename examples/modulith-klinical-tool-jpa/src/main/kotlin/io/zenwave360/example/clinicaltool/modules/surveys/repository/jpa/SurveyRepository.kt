package io.zenwave360.example.clinicaltool.modules.surveys.repository.jpa

import io.zenwave360.example.clinicaltool.modules.surveys.domain.*
import java.math.*
import java.time.*
import java.util.*

import org.springframework.data.jpa.repository.*
import org.springframework.stereotype.Repository

/**
 * Spring Data JPA repository for the Survey entity.
 */
@Suppress("unused")
@Repository
interface SurveyRepository : JpaRepository<Survey, Long> {fun findByNameAndHospitalId(name: String, hospitalId: Long): java.util.Optional<Survey>
}
