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
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

/** Service Implementation for managing [Survey, Question]. */
@Service
@Transactional(readOnly = true)
open class SurveysBackofficeServiceImpl(
    private val surveyRepository: SurveyRepository,
    private val questionRepository: QuestionRepository,
) : SurveysBackofficeService {

    private val log: Logger = LoggerFactory.getLogger(javaClass)

    private val surveysBackofficeServiceMapper: SurveysBackofficeServiceMapper = SurveysBackofficeServiceMapper.INSTANCE

    override fun listSurveys(): List<Survey> {
        log.debug("Request listSurveys")

        val surveys = surveyRepository.findAll()
        return surveys
    }

    override fun getSurvey(id: Long): Survey? {
        log.debug("[CRUD] Request to get Survey : {}", id)
        val survey = surveyRepository.findByIdOrNull(id)
        return survey
    }

    @Transactional
    override fun createSurvey(input: Survey): Survey {
        log.debug("[CRUD] Request to save Survey: {}", input)
        var survey = surveysBackofficeServiceMapper.update(Survey(), input)
        survey = surveyRepository.save(survey)
        // TODO: may need to reload the entity to fetch relationships 'mapped by id'
        return survey
    }

    @Transactional
    override fun updateSurvey(id: Long, input: Survey): Survey? {
        log.debug("Request updateSurvey: {} {}", id, input)

        return surveyRepository
            .findByIdOrNull(id)
            ?.let { existingSurvey -> surveysBackofficeServiceMapper.update(existingSurvey, input) }
            ?.let { surveyRepository.save(it) }
    }

    @Transactional
    override fun deleteSurvey(id: Long): Unit {
        log.debug("Request deleteSurvey: {}", id)

        log.debug("Request to delete Survey : {}", id)
        surveyRepository.deleteById(id)
    }

    override fun listQuestions(): List<Question> {
        log.debug("Request listQuestions")

        val questions = questionRepository.findAll()
        return questions
    }

    override fun getQuestion(id: Long): Question? {
        log.debug("[CRUD] Request to get Question : {}", id)
        val question = questionRepository.findByIdOrNull(id)
        return question
    }

    @Transactional
    override fun createQuestion(input: Question): Question {
        log.debug("[CRUD] Request to save Question: {}", input)
        var question = surveysBackofficeServiceMapper.update(Question(), input)
        question = questionRepository.save(question)
        // TODO: may need to reload the entity to fetch relationships 'mapped by id'
        return question
    }

    @Transactional
    override fun updateQuestion(id: Long, input: Question): Question? {
        log.debug("Request updateQuestion: {} {}", id, input)

        return questionRepository
            .findByIdOrNull(id)
            ?.let { existingQuestion -> surveysBackofficeServiceMapper.update(existingQuestion, input) }
            ?.let { questionRepository.save(it) }
    }

    @Transactional
    override fun deleteQuestion(id: Long): Unit {
        log.debug("Request deleteQuestion: {}", id)

        log.debug("Request to delete Question : {}", id)
        questionRepository.deleteById(id)
    }
}
