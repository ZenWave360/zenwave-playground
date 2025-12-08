package io.zenwave360.example.clinicaltool.modules.surveys.service.impl

import io.zenwave360.example.clinicaltool.modules.surveys.config.*
import io.zenwave360.example.clinicaltool.modules.surveys.domain.*
import io.zenwave360.example.clinicaltool.modules.surveys.repository.jpa.*
import io.zenwave360.example.clinicaltool.modules.surveys.repository.jpa.inmemory.*
import io.zenwave360.example.clinicaltool.modules.surveys.service.*
import io.zenwave360.example.clinicaltool.modules.surveys.service.dtos.*
import io.zenwave360.example.clinicaltool.modules.surveys.service.impl.mappers.*
import java.time.*
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mockito.*
import org.slf4j.Logger
import org.slf4j.LoggerFactory

/** Acceptance Test for SurveysBackofficeService. */
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
        assertNotNull(survey) // TODO: implement this test
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
        assertTrue(surveyRepository.containsEntity(survey)) // TODO: implement this test
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
        assertNotNull(survey)
        assertTrue(surveyRepository.containsEntity(survey!!)) // TODO: implement this test
    }

    @Test
    fun deleteSurveyTest() {
        val id: Long = 1L
        // assertTrue(surveyRepository.containsKey(id))
        surveysBackofficeService.deleteSurvey(id)
        // assertFalse(surveyRepository.containsKey(id))// TODO: implement this test
    }

    @Test
    fun listQuestionsTest() { // TODO: implement this test
        // val results = surveysBackofficeService.listQuestions(PageRequest.of(0, 10))
        // assertNotNull(results)
    }

    @Test
    fun getQuestionTest() { // TODO: implement this test
        val id: Long = 1L
        val question = surveysBackofficeService.getQuestion(id)
        assertNotNull(question)
    }

    @Test
    fun createQuestionTest() { // TODO: implement this test
        val input: Question = Question() // TODO
        // TODO fill input data
        // input.name = ""
        // input.questionType = QuestionType.values()[0]
        // input.required = true
        // input.rangeStart = 0 as Integer
        // input.rangeEnd = 0 as Integer
        // input.translations = List.of(QuestionTranslation())
        // input.options = List.of(Option())
        // input.includeOther = false
        val question = surveysBackofficeService.createQuestion(input)
        assertNotNull(question.id)
        assertTrue(questionRepository.containsEntity(question))
    }

    @Test
    fun updateQuestionTest() { // TODO: implement this test
        val id: Long = 1L
        val input: Question = Question() // TODO
        // TODO fill input data
        // input.name = ""
        // input.questionType = QuestionType.values()[0]
        // input.required = true
        // input.rangeStart = 0 as Integer
        // input.rangeEnd = 0 as Integer
        // input.translations = List.of(QuestionTranslation())
        // input.options = List.of(Option())
        // input.includeOther = false
        // assertTrue(questionRepository.containsKey(id))
        val question = surveysBackofficeService.updateQuestion(id, input)
        assertNotNull(question)
        assertTrue(questionRepository.containsEntity(question!!))
    }

    @Test
    fun deleteQuestionTest() { // TODO: implement this test
        val id: Long = 1L
        // assertTrue(questionRepository.containsKey(id))
        surveysBackofficeService.deleteQuestion(id)
        // assertFalse(questionRepository.containsKey(id))
    }
}
