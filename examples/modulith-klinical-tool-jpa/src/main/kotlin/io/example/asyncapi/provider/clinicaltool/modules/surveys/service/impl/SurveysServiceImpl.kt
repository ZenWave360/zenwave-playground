package io.example.asyncapi.provider.clinicaltool.modules.surveys.service.impl

import io.zenwave360.example.clinicaltool.modules.surveys.domain.*
import io.zenwave360.example.clinicaltool.modules.surveys.service.*
import io.zenwave360.example.clinicaltool.modules.surveys.service.dtos.*
import io.zenwave360.example.clinicaltool.modules.surveys.service.impl.mappers.*
import io.zenwave360.example.clinicaltool.modules.surveys.repository.jpa.*

import java.math.*
import java.time.*
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.Optional

/**
 * Service Implementation for managing [SurveyAnswers].
 */
@Service
@Transactional(readOnly = true)
open class SurveysServiceImpl(

    private val surveyAnswersRepository: SurveyAnswersRepository


) : SurveysService {

    private val log: Logger = LoggerFactory.getLogger(javaClass)

    private val surveysServiceMapper: SurveysServiceMapper = SurveysServiceMapper.INSTANCE





    override  fun getSurveyAndQuestionsForPatient(name: String, patientId: Long, lang: String?): SurveyAndQuestions
 {
    log.debug("Request getSurveyAndQuestionsForPatient: {} {} {}", name, patientId, lang);

    val surveyAnswers = surveysServiceMapper.update(SurveyAnswers(), name, patientId, lang)
    // TODO: implement this method
    return surveysServiceMapper.asSurveyAndQuestions(surveyAnswers)


}


    override  fun answerSurvey(surveyId: Long, patientId: Long, date: LocalDate, input: SurveyAnswers): SurveyAnswers
 {
    log.debug("Request answerSurvey: {} {} {} {}", surveyId, patientId, date, input);

    val surveyAnswers = surveyAnswersRepository.findBySurveyIdAndPatientIdAndDate(surveyId, patientId, date).map { existingSurveyAnswers ->
        surveysServiceMapper.update(existingSurveyAnswers, input)
    }
    .map { surveyAnswersRepository.save(it) }
    .orElseThrow()
    return surveyAnswers


}

    @Transactional

    override  fun updateSurveyAnswers(surveyId: Long, patientId: Long, date: LocalDate, input: java.util.Map<String,Any?>): Optional<SurveyAnswers>
 {
    log.debug("Request updateSurveyAnswers: {} {} {} {}", surveyId, patientId, date, input);

    val surveyAnswers = surveyAnswersRepository.findBySurveyIdAndPatientIdAndDate(surveyId, patientId, date).map { existingSurveyAnswers ->
        surveysServiceMapper.update(existingSurveyAnswers, input)
    }
    .map { surveyAnswersRepository.save(it) }
    return surveyAnswers


}


    override  fun getSurveyAnswers(surveyId: Long, patientId: Long, date: LocalDate): Optional<SurveyAnswers>
 {
    log.debug("Request getSurveyAnswers: {} {} {}", surveyId, patientId, date);
    val surveyAnswers = surveyAnswersRepository.findBySurveyIdAndPatientIdAndDate(surveyId, patientId, date)
    return surveyAnswers


}



}
