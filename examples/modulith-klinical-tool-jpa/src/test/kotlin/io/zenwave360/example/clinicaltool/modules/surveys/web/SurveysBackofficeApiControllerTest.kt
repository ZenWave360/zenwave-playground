package io.zenwave360.example.clinicaltool.modules.surveys.web

import io.zenwave360.example.clinicaltool.modules.surveys.web.*
import io.zenwave360.example.clinicaltool.modules.surveys.web.dtos.*
import io.zenwave360.example.clinicaltool.modules.surveys.config.ServicesInMemoryConfig

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.slf4j.Logger
import org.slf4j.LoggerFactory

import java.math.*
import java.time.*
import java.util.*

/**
 * Test controller for SurveysBackofficeApiController.
 */
class SurveysBackofficeApiControllerTest {

    private val log: Logger = LoggerFactory.getLogger(javaClass)

    private val context = ServicesInMemoryConfig()

    private val controller = SurveysBackofficeApiController( context.surveysBackofficeService() )

    @BeforeEach
    fun setUp() {
        context.reloadTestData()
    }


    @Test
    fun listSurveysTest() {

        val response = controller.listSurveys()
        Assertions.assertEquals(200, response.statusCode.value())
    }

    @Test
    fun createSurveyTest() {
        val reqBody: SurveyDTO = SurveyDTO(name = "aaa", hospitalId = 1L, title = "aaa", lang = "aaa")
        val response = controller.createSurvey(reqBody)
        Assertions.assertEquals(201, response.statusCode.value())
    }

    @Test
    fun getSurveyTest() {
        val id: Long = 1L
        val response = controller.getSurvey(id)
        Assertions.assertEquals(200, response.statusCode.value())
    }

    @Test
    fun updateSurveyTest() {
        val id: Long = 1L
val reqBody: SurveyDTO = SurveyDTO(name = "aaa", hospitalId = 1L, title = "aaa", lang = "aaa")
        val response = controller.updateSurvey(id, reqBody)
        Assertions.assertEquals(200, response.statusCode.value())
    }

    @Test
    fun deleteSurveyTest() {
        val id: Long = 1L
        val response = controller.deleteSurvey(id)
        Assertions.assertEquals(204, response.statusCode.value())
    }

    @Test
    fun listQuestionsTest() {

        val response = controller.listQuestions()
        Assertions.assertEquals(200, response.statusCode.value())
    }

    @Test
    fun createQuestionTest() {
        val reqBody: QuestionDTO = QuestionDTO(name = "aaa", questionType = QuestionTypeDTO.YES_NO)
        val response = controller.createQuestion(reqBody)
        Assertions.assertEquals(201, response.statusCode.value())
    }

    @Test
    fun getQuestionTest() {
        val id: Long = 1L
        val response = controller.getQuestion(id)
        Assertions.assertEquals(200, response.statusCode.value())
    }

    @Test
    fun updateQuestionTest() {
        val id: Long = 1L
val reqBody: QuestionDTO = QuestionDTO(name = "aaa", questionType = QuestionTypeDTO.YES_NO)
        val response = controller.updateQuestion(id, reqBody)
        Assertions.assertEquals(200, response.statusCode.value())
    }

    @Test
    fun deleteQuestionTest() {
        val id: Long = 1L
        val response = controller.deleteQuestion(id)
        Assertions.assertEquals(204, response.statusCode.value())
    }


}
