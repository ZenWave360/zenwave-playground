package io.zenwave360.example.clinicaltool.modules.surveys.repository.jpa

import io.zenwave360.example.clinicaltool.modules.surveys.domain.*
import java.math.*
import java.time.*
import java.util.*

import org.springframework.data.jpa.repository.*
import org.springframework.stereotype.Repository

/**
 * Spring Data JPA repository for the SurveyAnswers entity.
 */
@Suppress("unused")
@Repository
interface SurveyAnswersRepository : JpaRepository<SurveyAnswers, Long> {fun findBySurveyIdAndPatientIdAndDate(surveyId: Long, patientId: Long, date: LocalDate): java.util.Optional<SurveyAnswers>
}
