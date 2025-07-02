package io.zenwave360.example.clinicaltool.modules.surveys.repository.jpa.inmemory

import io.zenwave360.example.clinicaltool.modules.surveys.domain.*
import io.zenwave360.example.clinicaltool.modules.surveys.repository.jpa.SurveyAnswersRepository
import java.math.*
import java.time.*
import java.util.*

class SurveyAnswersRepositoryInMemory : InMemoryJpaRepository<SurveyAnswers, Long>(), SurveyAnswersRepository {
    override fun findBySurveyIdAndPatientIdAndDate(surveyId: Long, patientId: Long, date: LocalDate): java.util.Optional<SurveyAnswers> {
        return getEntities().values.stream().filter { e ->
             isSameValue(surveyId, readField(e, "surveyId")) && isSameValue(patientId, readField(e, "patientId")) && isSameValue(date, readField(e, "date")) 
        }.findFirst()
    }
}
