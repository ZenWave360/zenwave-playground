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
 * Test controller for SurveysApiController.
 */
class SurveysApiControllerTest {

    private val log: Logger = LoggerFactory.getLogger(javaClass)

    private val context = ServicesInMemoryConfig()

    private val controller = SurveysApiController( context.surveysService() )

    @BeforeEach
    fun setUp() {
        context.reloadTestData()
    }


    @Test
    fun getSurveyAndQuestionsForPatientTest() {
        val name: String = ""
val patientId: Long = 0
val lang: String = ""
        val response = controller.getSurveyAndQuestionsForPatient(name, patientId, lang)
        Assertions.assertEquals(200, response.statusCode.value())
    }

    @Test
    fun answerSurveyTest() {
        val surveyId: Long = 0
val patientId: Long = 0
val date: LocalDate = LocalDate.now()
val reqBody: SurveyAnswersDTO = SurveyAnswersDTO(surveyId = "", patientId = "", date = "", lang = "")
        val response = controller.answerSurvey(surveyId, patientId, date, reqBody)
        Assertions.assertEquals(201, response.statusCode.value())
    }

    @Test
    fun updateSurveyAnswersTest() {
        val surveyId: Long = 0
val patientId: Long = 0
val date: LocalDate = LocalDate.now()
val surveryId: Long = 0
val input: Map = Map(surveyId = "", patientId = "", date = "", lang = "", surveyId = "", patientId = "", date = "", lang = "")
        val response = controller.updateSurveyAnswers(surveyId, patientId, date, surveryId, input)
        Assertions.assertEquals(200, response.statusCode.value())
    }

    @Test
    fun getSurveyAnswersTest() {
        val surveyId: Long = 0
val patientId: Long = 0
val date: LocalDate = LocalDate.now()
        val response = controller.getSurveyAnswers(surveyId, patientId, date)
        Assertions.assertEquals(200, response.statusCode.value())
    }


}
