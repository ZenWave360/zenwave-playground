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
import java.util.List;

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
    }

    @Test
    void getSurveyTest() {
        Long id = 1L;
        var survey = surveysBackofficeService.getSurvey(id);
        assertTrue(survey.isPresent());
    }

    @Test
    void createSurveyTest() {
        Survey input = new Survey();
        input.setName("patient-survey");
        input.setHospitalId(1L);
        input.setTitle("Patient Survey");
        input.setLang("en");

        SurveySection section = new SurveySection();
        section.setName("general");
        section.setQuestionIds(List.of(1L, 2L));
        input.setSections(List.of(section));

        var survey = surveysBackofficeService.createSurvey(input);
        assertNotNull(survey.getId());
        assertTrue(surveyRepository.containsEntity(survey));
    }

    @Test
    void updateSurveyTest() {
        Long id = 1L;
        Survey input = new Survey();
        input.setName("updated-survey");
        input.setHospitalId(1L);
        input.setTitle("Updated Survey");
        input.setLang("es");

        SurveySection section = new SurveySection();
        section.setName("updated-section");
        section.setQuestionIds(List.of(3L, 4L));
        input.setSections(List.of(section));

        var survey = surveysBackofficeService.updateSurvey(id, input);
        assertTrue(survey.isPresent());
        assertTrue(surveyRepository.containsEntity(survey.get()));
    }

    @Test
    void deleteSurveyTest() {
        Long id = 1L;
        surveysBackofficeService.deleteSurvey(id);
    }

    @Test
    void listQuestionsTest() {
    }

    @Test
    void getQuestionTest() {
        Long id = 1L;
        var question = surveysBackofficeService.getQuestion(id);
        assertTrue(question.isPresent());
    }

    @Test
    void createQuestionTest() {
        Question input = new Question();
        input.setName("satisfaction-question");
        input.setQuestionType(QuestionType.SINGLE_SELECTION);
        input.setRequired(true);
        input.setRangeStart(1);
        input.setRangeEnd(10);

        QuestionTranslation translation = new QuestionTranslation();
        translation.setLang("en");
        translation.setText("How satisfied are you?");
        input.setTranslations(List.of(translation));

        Option option = new Option();
        option.setName("very-satisfied");
        input.setOptions(List.of(option));

        input.setIncludeOther(false);
        var question = surveysBackofficeService.createQuestion(input);
        assertNotNull(question.getId());
        assertTrue(questionRepository.containsEntity(question));
    }

    @Test
    void updateQuestionTest() {
        Long id = 1L;
        Question input = new Question();
        input.setName("updated-question");
        input.setQuestionType(QuestionType.MULTIPLE_SELECTION);
        input.setRequired(false);
        input.setRangeStart(0);
        input.setRangeEnd(5);

        QuestionTranslation translation = new QuestionTranslation();
        translation.setLang("es");
        translation.setText("¿Qué tan satisfecho está?");
        input.setTranslations(List.of(translation));

        Option option = new Option();
        option.setName("satisfied");
        input.setOptions(List.of(option));

        input.setIncludeOther(true);
        var question = surveysBackofficeService.updateQuestion(id, input);
        assertTrue(question.isPresent());
        assertTrue(questionRepository.containsEntity(question.get()));
    }

    @Test
    void deleteQuestionTest() {
        Long id = 1L;
        surveysBackofficeService.deleteQuestion(id);
    }
}
