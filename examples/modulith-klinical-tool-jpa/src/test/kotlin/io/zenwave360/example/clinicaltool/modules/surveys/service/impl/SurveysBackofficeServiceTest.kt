package io.zenwave360.example.clinicaltool.modules.surveys.service.impl

import io.zenwave360.example.clinicaltool.modules.surveys.config.*
import io.zenwave360.example.clinicaltool.modules.surveys.domain.*
import io.zenwave360.example.clinicaltool.modules.surveys.service.*
import io.zenwave360.example.clinicaltool.modules.surveys.service.dtos.*
import io.zenwave360.example.clinicaltool.modules.surveys.service.impl.mappers.*
import io.zenwave360.example.clinicaltool.modules.surveys.repository.jpa.*
import io.zenwave360.example.clinicaltool.modules.surveys.repository.jpa.inmemory.*

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mapstruct.factory.Mappers
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.PageRequest

import java.util.Map
import java.util.Optional
import java.time.*
import java.math.BigDecimal

import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Assertions.assertFalse
import org.mockito.Mockito.*

/**
 * Acceptance Test for SurveysBackofficeService.
 */
class SurveysBackofficeServiceTest {

    private val log: Logger = LoggerFactory.getLogger(javaClass)

    val context = ServicesInMemoryConfig()
    val surveysBackofficeService: SurveysBackofficeService = context.surveysBackofficeService()

    val surveyRepository: SurveyRepositoryInMemory = context.surveyRepository()

    val questionRepository: QuestionRepositoryInMemory = context.questionRepository()


    @BeforeEach
    fun setUp() {
        context.reloadTestData()
    }


    @Test
    fun listSurveysTest() {
        // val results = surveysBackofficeService.listSurveys(PageRequest.of(0, 10))
        // assertNotNull(results)// TODO: implement this test
}

    @Test
    fun getSurveyTest() {
        val id: Long = 1L
        val survey = surveysBackofficeService.getSurvey(id)
        assertTrue(survey.isPresent)// TODO: implement this test
}

    @Test
    fun createSurveyTest() {
        val input: Survey = Survey() // TODO
        // TODO fill input data
        // input.name = ""
        // input.hospitalId = 0L
        // input.title = ""
        // input.lang = ""
        // input.sections = List.of(SurveySection())
        val survey = surveysBackofficeService.createSurvey(input)
        assertNotNull(survey.id)
        assertTrue(surveyRepository.containsEntity(survey))// TODO: implement this test
}

    @Test
    fun updateSurveyTest() {
        val id: Long = 1L
        val input: Survey = Survey() // TODO
        // TODO fill input data
        // input.name = ""
        // input.hospitalId = 0L
        // input.title = ""
        // input.lang = ""
        // input.sections = List.of(SurveySection())
        // assertTrue(surveyRepository.containsKey(id))
        val survey = surveysBackofficeService.updateSurvey(id, input)
        assertTrue(survey.isPresent)
        assertTrue(surveyRepository.containsEntity(survey.get()))// TODO: implement this test
}

    @Test
    fun deleteSurveyTest() {
        val id: Long = 1L
        // assertTrue(surveyRepository.containsKey(id))
        surveysBackofficeService.deleteSurvey(id)
        // assertFalse(surveyRepository.containsKey(id))// TODO: implement this test
}

    @Test
    fun listQuestionsTest() {// TODO: implement this test
        // val results = surveysBackofficeService.listQuestions(PageRequest.of(0, 10))
        // assertNotNull(results)
}

    @Test
    fun getQuestionTest() {// TODO: implement this test
        val id: Long = 1L
        val question = surveysBackofficeService.getQuestion(id)
        assertTrue(question.isPresent)
}

    @Test
    fun createQuestionTest() {// TODO: implement this test
        val input: Question = Question() // TODO
        // TODO fill input data
        // input.name = ""
        // input.questionType = QuestionType.values()[0]
        // input.required = true
        // input.rangeStart = 0
        // input.rangeEnd = 0
        // input.translations = List.of(QuestionTranslation())
        // input.options = List.of(Option())
        // input.includeOther = false
        val question = surveysBackofficeService.createQuestion(input)
        assertNotNull(question.id)
        assertTrue(questionRepository.containsEntity(question))
}

    @Test
    fun updateQuestionTest() {// TODO: implement this test
        val id: Long = 1L
        val input: Question = Question() // TODO
        // TODO fill input data
        // input.name = ""
        // input.questionType = QuestionType.values()[0]
        // input.required = true
        // input.rangeStart = 0
        // input.rangeEnd = 0
        // input.translations = List.of(QuestionTranslation())
        // input.options = List.of(Option())
        // input.includeOther = false
        // assertTrue(questionRepository.containsKey(id))
        val question = surveysBackofficeService.updateQuestion(id, input)
        assertTrue(question.isPresent)
        assertTrue(questionRepository.containsEntity(question.get()))
}

    @Test
    fun deleteQuestionTest() {// TODO: implement this test
        val id: Long = 1L
        // assertTrue(questionRepository.containsKey(id))
        surveysBackofficeService.deleteQuestion(id)
        // assertFalse(questionRepository.containsKey(id))
}

}
