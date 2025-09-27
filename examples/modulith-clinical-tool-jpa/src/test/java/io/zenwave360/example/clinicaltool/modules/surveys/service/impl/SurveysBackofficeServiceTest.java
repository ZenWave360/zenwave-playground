package io.zenwave360.example.clinicaltool.modules.surveys.service.impl;

import static org.junit.jupiter.api.Assertions.assertNotNull;
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
 * Acceptance Test for SurveysBackofficeService.
 */
class SurveysBackofficeServiceTest {

    private final Logger log = LoggerFactory.getLogger(getClass());

    ServicesInMemoryConfig context = new ServicesInMemoryConfig();
    SurveysBackofficeServiceImpl surveysBackofficeService = context.surveysBackofficeService();

    SurveyRepositoryInMemory surveyRepository = context.surveyRepository();

    QuestionRepositoryInMemory questionRepository = context.questionRepository();

    @BeforeEach
    void setUp() {
        context.reloadTestData();
    }

    @Test
    void listSurveysTest() {
        // var results = surveysBackofficeService.listSurveys(PageRequest.of(0, 10));
        // assertNotNull(results);// TODO: implement this test
    }

    @Test
    void getSurveyTest() {
        Long id = null;
        var survey = surveysBackofficeService.getSurvey(id);
        assertTrue(survey.isPresent()); // TODO: implement this test
    }

    @Test
    void createSurveyTest() {
        Survey input = null; // TODO
        // TODO fill input data
        // input.setName("");
        // input.setHospitalId(0L);
        // input.setTitle("");
        // input.setLang("");
        // input.setSections(List.of(new SurveySection()));
        var survey = surveysBackofficeService.createSurvey(input);
        assertNotNull(survey.getId());
        assertTrue(surveyRepository.containsEntity(survey)); // TODO: implement this test
    }

    @Test
    void updateSurveyTest() {
        Long id = null;
        Survey input = null; // TODO
        // TODO fill input data
        // input.setName("");
        // input.setHospitalId(0L);
        // input.setTitle("");
        // input.setLang("");
        // input.setSections(List.of(new SurveySection()));
        // assertTrue(surveyRepository.containsKey(id));
        var survey = surveysBackofficeService.updateSurvey(id, input);
        assertTrue(survey.isPresent());
        assertTrue(surveyRepository.containsEntity(survey.get())); // TODO: implement this test
    }

    @Test
    void deleteSurveyTest() {
        Long id = null;
        // assertTrue(surveyRepository.containsKey(id));
        surveysBackofficeService.deleteSurvey(id);
        // assertFalse(surveyRepository.containsKey(id));// TODO: implement this test
    }

    @Test
    void listQuestionsTest() { // TODO: implement this test
        // var results = surveysBackofficeService.listQuestions(PageRequest.of(0, 10));
        // assertNotNull(results);
    }

    @Test
    void getQuestionTest() { // TODO: implement this test
        Long id = null;
        var question = surveysBackofficeService.getQuestion(id);
        assertTrue(question.isPresent());
    }

    @Test
    void createQuestionTest() { // TODO: implement this test
        Question input = null; // TODO
        // TODO fill input data
        // input.setName("");
        // input.setQuestionType(QuestionType.values()[0]);
        // input.setRequired(true);
        // input.setRangeStart(0);
        // input.setRangeEnd(0);
        // input.setTranslations(List.of(new QuestionTranslation()));
        // input.setOptions(List.of(new Option()));
        // input.setIncludeOther(false);
        var question = surveysBackofficeService.createQuestion(input);
        assertNotNull(question.getId());
        assertTrue(questionRepository.containsEntity(question));
    }

    @Test
    void updateQuestionTest() { // TODO: implement this test
        Long id = null;
        Question input = null; // TODO
        // TODO fill input data
        // input.setName("");
        // input.setQuestionType(QuestionType.values()[0]);
        // input.setRequired(true);
        // input.setRangeStart(0);
        // input.setRangeEnd(0);
        // input.setTranslations(List.of(new QuestionTranslation()));
        // input.setOptions(List.of(new Option()));
        // input.setIncludeOther(false);
        // assertTrue(questionRepository.containsKey(id));
        var question = surveysBackofficeService.updateQuestion(id, input);
        assertTrue(question.isPresent());
        assertTrue(questionRepository.containsEntity(question.get()));
    }

    @Test
    void deleteQuestionTest() { // TODO: implement this test
        Long id = null;
        // assertTrue(questionRepository.containsKey(id));
        surveysBackofficeService.deleteQuestion(id);
        // assertFalse(questionRepository.containsKey(id));
    }
}
