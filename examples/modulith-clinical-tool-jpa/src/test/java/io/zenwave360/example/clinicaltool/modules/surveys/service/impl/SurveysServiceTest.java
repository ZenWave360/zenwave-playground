package io.zenwave360.example.clinicaltool.modules.surveys.service.impl;

import io.zenwave360.example.clinicaltool.modules.surveys.config.*;
import io.zenwave360.example.clinicaltool.modules.surveys.domain.*;
import io.zenwave360.example.clinicaltool.modules.surveys.service.*;
import io.zenwave360.example.clinicaltool.modules.surveys.service.dtos.*;
import io.zenwave360.example.clinicaltool.modules.surveys.service.impl.mappers.*;
import io.zenwave360.example.clinicaltool.modules.surveys.repository.jpa.*;
import io.zenwave360.example.clinicaltool.modules.surveys.repository.jpa.inmemory.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;

import java.util.Map;
import java.util.Optional;
import java.time.*;
import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.*;

/**
 * Acceptance Test for SurveysService.
 */
class SurveysServiceTest  {

    private final Logger log = LoggerFactory.getLogger(getClass());

    ServicesInMemoryConfig context = new ServicesInMemoryConfig();
    SurveysServiceImpl surveysService = context.surveysService();

    SurveyAnswersRepositoryInMemory surveyAnswersRepository = context.surveyAnswersRepository();

	@BeforeEach
	void setUp() {
		context.reloadTestData();
	}



    @Test
    void getSurveyAndQuestionsForPatientTest() {// TODO: implement this test
}

    @Test
    void answerSurveyTest() {// TODO: implement this test
}

    @Test
    void updateSurveyAnswersTest() {
        var surveyId = 0L;
var patientId = 0L;
var date = LocalDate.now();
        java.util.Map input = null; // TODO
        // TODO fill input data
        // input.setSurveyId(0L);
        // input.setPatientId(0L);
        // input.setDate(LocalDate.now());
        // input.setLang("");
        // input.setAnswers(List.of(new Answer()));
        // assertTrue(surveyAnswersRepository.containsKey(id));
        var surveyAnswers = surveysService.updateSurveyAnswers(surveyId, patientId, date, input);
        assertTrue(surveyAnswers.isPresent());
        assertTrue(surveyAnswersRepository.containsEntity(surveyAnswers.get()));
}

    @Test
    void getSurveyAnswersTest() {
        var surveyId = 0L;
var patientId = 0L;
var date = LocalDate.now();
        var surveyAnswers = surveysService.getSurveyAnswers(surveyId, patientId, date);
        assertTrue(surveyAnswers.isPresent());
}

}
