package io.zenwave360.example.clinicaltool.modules.surveys.service.impl;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

import io.zenwave360.example.clinicaltool.modules.surveys.config.*;
import io.zenwave360.example.clinicaltool.modules.surveys.domain.*;
import io.zenwave360.example.clinicaltool.modules.surveys.repository.jpa.*;
import io.zenwave360.example.clinicaltool.modules.surveys.repository.jpa.inmemory.*;
import io.zenwave360.example.clinicaltool.modules.surveys.service.*;
import io.zenwave360.example.clinicaltool.modules.surveys.service.dtos.*;
import io.zenwave360.example.clinicaltool.modules.surveys.service.impl.mappers.*;
import java.time.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Acceptance Test for SurveysService.
 */
class SurveysServiceTest {

    private final Logger log = LoggerFactory.getLogger(getClass());

    ServicesInMemoryConfig context = new ServicesInMemoryConfig();
    SurveysServiceImpl surveysService = context.surveysService();

    SurveyAnswersRepositoryInMemory surveyAnswersRepository = context.surveyAnswersRepository();

    @BeforeEach
    void setUp() {
        context.reloadTestData();
    }

    @Test
    void getSurveyAndQuestionsForPatientTest() {
    }

    @Test
    void answerSurveyTest() {
    }

    @Test
    void updateSurveyAnswersTest() {
        var surveyId = 1L;
        var patientId = 1L;
        var date = LocalDate.of(2025, 12, 12);
        java.util.Map input = new java.util.HashMap();
        var surveyAnswers = surveysService.updateSurveyAnswers(surveyId, patientId, date, input);
        assertTrue(surveyAnswers.isPresent());
        assertTrue(surveyAnswersRepository.containsEntity(surveyAnswers.get()));
    }

    @Test
    void getSurveyAnswersTest() {
        var surveyId = 1L;
        var patientId = 1L;
        var date = LocalDate.of(2025, 12, 12);
        var surveyAnswers = surveysService.getSurveyAnswers(surveyId, patientId, date);
        assertTrue(surveyAnswers.isPresent());
    }
}
