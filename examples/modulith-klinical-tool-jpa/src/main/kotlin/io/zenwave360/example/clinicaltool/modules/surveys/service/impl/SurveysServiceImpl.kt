package io.zenwave360.example.clinicaltool.modules.surveys.service.impl

import io.zenwave360.example.clinicaltool.modules.surveys.domain.*
import io.zenwave360.example.clinicaltool.modules.surveys.repository.jpa.*
import io.zenwave360.example.clinicaltool.modules.surveys.service.*
import io.zenwave360.example.clinicaltool.modules.surveys.service.dtos.*
import io.zenwave360.example.clinicaltool.modules.surveys.service.impl.mappers.*
import java.math.*
import java.time.*
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

/** Service Implementation for managing [SurveyAnswers]. */
@Service
@Transactional(readOnly = true)
open class SurveysServiceImpl(private val surveyAnswersRepository: SurveyAnswersRepository) : SurveysService {

    private val log: Logger = LoggerFactory.getLogger(javaClass)

    private val surveysServiceMapper: SurveysServiceMapper = SurveysServiceMapper.INSTANCE

    override fun getSurveyAndQuestionsForPatient(name: String, patientId: Long, lang: String?): SurveyAndQuestions {
        log.debug("Request getSurveyAndQuestionsForPatient: {} {} {}", name, patientId, lang)

        val surveyAnswers = surveysServiceMapper.update(SurveyAnswers(), name, patientId, lang)
        // TODO: implement this method
        return surveysServiceMapper.asSurveyAndQuestions(surveyAnswers)
    }

    override fun answerSurvey(surveyId: Long, patientId: Long, date: LocalDate, input: SurveyAnswers): SurveyAnswers {
        log.debug("Request answerSurvey: {} {} {} {}", surveyId, patientId, date, input)

        return surveyAnswersRepository
            .findBySurveyIdAndPatientIdAndDate(surveyId, patientId, date)
            ?.let { existingSurveyAnswers -> surveysServiceMapper.update(existingSurveyAnswers, input) }
            ?.let { surveyAnswersRepository.save(it) }
            ?: throw NoSuchElementException(
                "SurveyAnswers not found with id: surveyId=$surveyId, patientId=$patientId, date=$date"
            )
    }

    @Transactional
    override fun updateSurveyAnswers(
        surveyId: Long,
        patientId: Long,
        date: LocalDate,
        input: Map<String, Any?>,
    ): SurveyAnswers? {
        log.debug("Request updateSurveyAnswers: {} {} {} {}", surveyId, patientId, date, input)

        return surveyAnswersRepository
            .findBySurveyIdAndPatientIdAndDate(surveyId, patientId, date)
            ?.let { existingSurveyAnswers -> surveysServiceMapper.update(existingSurveyAnswers, input) }
            ?.let { surveyAnswersRepository.save(it) }
    }

    override fun getSurveyAnswers(surveyId: Long, patientId: Long, date: LocalDate): SurveyAnswers? {
        log.debug("Request getSurveyAnswers: {} {} {}", surveyId, patientId, date)
        val surveyAnswers = surveyAnswersRepository.findBySurveyIdAndPatientIdAndDate(surveyId, patientId, date)
        return surveyAnswers
    }
}
