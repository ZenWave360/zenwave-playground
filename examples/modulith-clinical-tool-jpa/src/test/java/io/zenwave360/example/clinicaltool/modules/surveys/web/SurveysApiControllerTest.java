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
        Long patientId = 1L;
        String lang = "en";
        var response = controller.getSurveyAndQuestionsForPatient(name, patientId, lang);
        Assertions.assertEquals(200, response.getStatusCode().value());
    }

    @Test
    public void answerSurveyTest() {
        Long surveyId = 1L;
        Long patientId = 1L;
        LocalDate date = LocalDate.of(2025, 12, 12);
        SurveyAnswersDTO reqBody = new SurveyAnswersDTO();
        reqBody.setSurveyId(1L);
        reqBody.setPatientId(1L);
        reqBody.setDate(LocalDate.of(2025, 12, 12));
        reqBody.setLang("en");

        List<AnswerDTO> answers = new ArrayList<>();

        AnswerDTO answer1 = new AnswerDTO();
        answer1.setQuestionId(1L);
        answer1.setValue("8");
        answers.add(answer1);

        AnswerDTO answer2 = new AnswerDTO();
        answer2.setQuestionId(2L);
        answer2.setValues(List.of("option1", "option2"));
        answers.add(answer2);

        AnswerDTO answer3 = new AnswerDTO();
        answer3.setQuestionId(3L);
        answer3.setValue("Very satisfied with the care received");
        answers.add(answer3);

        reqBody.setAnswers(answers);
        var response = controller.answerSurvey(surveyId, patientId, date, reqBody);
        Assertions.assertEquals(201, response.getStatusCode().value());
    }

    @Test
    public void updateSurveyAnswersTest() {
        Long surveyId = 1L;
        Long patientId = 1L;
        LocalDate date = LocalDate.of(2025, 12, 12);
        Map<String, Object> input = new HashMap<>();
        input.put("lang", "en");
        input.put("answers[0].questionId", 1L);
        input.put("answers[0].value", "8");
        input.put("answers[1].questionId", 2L);
        input.put("answers[1].values", List.of("option1", "option2"));
        input.put("answers[2].questionId", 3L);
        input.put("answers[2].value", "Very satisfied with the care received");
        var response = controller.updateSurveyAnswers(surveyId, patientId, date, input);
        Assertions.assertEquals(200, response.getStatusCode().value());
    }

    @Test
    public void getSurveyAnswersTest() {
        Long surveyId = 1L;
        Long patientId = 1L;
        LocalDate date = LocalDate.of(2025, 12, 12);
        var response = controller.getSurveyAnswers(surveyId, patientId, date);
        Assertions.assertEquals(200, response.getStatusCode().value());
    }
}
