package io.example.asyncapi.provider.clinicaltool.modules.surveys.service.impl

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
 * Acceptance Test for SurveysService.
 */
class SurveysServiceTest {

    private val log: Logger = LoggerFactory.getLogger(javaClass)

    val context = ServicesInMemoryConfig()
    val surveysService: SurveysService = context.surveysService()

    val surveyAnswersRepository: SurveyAnswersRepositoryInMemory = context.surveyAnswersRepository()


    @BeforeEach
    fun setUp() {
        context.reloadTestData()
    }


    @Test
    fun getSurveyAndQuestionsForPatientTest() {// TODO: implement this test
}

    @Test
    fun answerSurveyTest() {// TODO: implement this test
}

    @Test
    fun updateSurveyAnswersTest() {
        val surveyId = 0L;
val patientId = 0L;
val date = LocalDate.now();
        val input = java.util.HashMap<String,Any?>()
        // TODO fill input data
        // input.surveyId = 0L
        // input.patientId = 0L
        // input.date = LocalDate.now()
        // input.lang = ""
        // input.answers = List.of(Answer())
        // assertTrue(surveyAnswersRepository.containsKey(id))
        val surveyAnswers = surveysService.updateSurveyAnswers(surveyId, patientId, date, input as Map<String, Any?>)
        assertTrue(surveyAnswers.isPresent)
        assertTrue(surveyAnswersRepository.containsEntity(surveyAnswers.get()))
}

    @Test
    fun getSurveyAnswersTest() {
        val surveyId = 0L;
val patientId = 0L;
val date = LocalDate.now();
        val surveyAnswers = surveysService.getSurveyAnswers(surveyId, patientId, date)
        assertTrue(surveyAnswers.isPresent)
}

}
