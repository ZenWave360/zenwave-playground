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
 * Test controller for SurveysBackofficeApiController.
 */
public class SurveysBackofficeApiControllerTest {

    private final Logger log = LoggerFactory.getLogger(getClass());

    ServicesInMemoryConfig context = new ServicesInMemoryConfig();

    SurveysBackofficeApiController controller = new SurveysBackofficeApiController(context.surveysBackofficeService());

    @BeforeEach
    void setUp() {
        context.reloadTestData();
    }

    @Test
    public void listSurveysTest() {

        var response = controller.listSurveys();
        Assertions.assertEquals(200, response.getStatusCode().value());
    }

    @Test
    public void createSurveyTest() {
        SurveyDTO reqBody = null;
        var response = controller.createSurvey(reqBody);
        Assertions.assertEquals(201, response.getStatusCode().value());
    }

    @Test
    public void getSurveyTest() {
        Long id = null;
        var response = controller.getSurvey(id);
        Assertions.assertEquals(200, response.getStatusCode().value());
    }

    @Test
    public void updateSurveyTest() {
        Long id = null;
        SurveyDTO reqBody = null;
        var response = controller.updateSurvey(id, reqBody);
        Assertions.assertEquals(200, response.getStatusCode().value());
    }

    @Test
    public void deleteSurveyTest() {
        Long id = null;
        var response = controller.deleteSurvey(id);
        Assertions.assertEquals(204, response.getStatusCode().value());
    }

    @Test
    public void listQuestionsTest() {

        var response = controller.listQuestions();
        Assertions.assertEquals(200, response.getStatusCode().value());
    }

    @Test
    public void createQuestionTest() {
        QuestionDTO reqBody = null;
        var response = controller.createQuestion(reqBody);
        Assertions.assertEquals(201, response.getStatusCode().value());
    }

    @Test
    public void getQuestionTest() {
        Long id = null;
        var response = controller.getQuestion(id);
        Assertions.assertEquals(200, response.getStatusCode().value());
    }

    @Test
    public void updateQuestionTest() {
        Long id = null;
        QuestionDTO reqBody = null;
        var response = controller.updateQuestion(id, reqBody);
        Assertions.assertEquals(200, response.getStatusCode().value());
    }

    @Test
    public void deleteQuestionTest() {
        Long id = null;
        var response = controller.deleteQuestion(id);
        Assertions.assertEquals(204, response.getStatusCode().value());
    }
}
