package io.zenwave360.example.clinicaltool.modules.surveys.web;

import io.zenwave360.example.clinicaltool.modules.surveys.config.ServicesInMemoryConfig;
import io.zenwave360.example.clinicaltool.modules.surveys.web.dtos.*;
import java.math.*;
import java.time.*;
import java.util.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Test controller for SurveysApiController.
 */
public class SurveysApiControllerTest {

    private final Logger log = LoggerFactory.getLogger(getClass());

    ServicesInMemoryConfig context = new ServicesInMemoryConfig();

    SurveysApiController controller = new SurveysApiController(context.surveysService());

    @BeforeEach
    void setUp() {
        context.reloadTestData();
    }

    @Test
    public void getSurveyAndQuestionsForPatientTest() {
        String name = null;
        Long patientId = null;
        String lang = null;
        var response = controller.getSurveyAndQuestionsForPatient(name, patientId, lang);
        Assertions.assertEquals(200, response.getStatusCode().value());
    }

    @Test
    public void answerSurveyTest() {
        Long surveyId = null;
        Long patientId = null;
        LocalDate date = null;
        SurveyAnswersDTO reqBody = null;
        var response = controller.answerSurvey(surveyId, patientId, date, reqBody);
        Assertions.assertEquals(201, response.getStatusCode().value());
    }

    @Test
    public void updateSurveyAnswersTest() {
        Long surveyId = null;
        Long patientId = null;
        LocalDate date = null;
        Long surveryId = null;
        Map input = null;
        var response = controller.updateSurveyAnswers(surveyId, patientId, date, surveryId, input);
        Assertions.assertEquals(200, response.getStatusCode().value());
    }

    @Test
    public void getSurveyAnswersTest() {
        Long surveyId = null;
        Long patientId = null;
        LocalDate date = null;
        var response = controller.getSurveyAnswers(surveyId, patientId, date);
        Assertions.assertEquals(200, response.getStatusCode().value());
    }
}
