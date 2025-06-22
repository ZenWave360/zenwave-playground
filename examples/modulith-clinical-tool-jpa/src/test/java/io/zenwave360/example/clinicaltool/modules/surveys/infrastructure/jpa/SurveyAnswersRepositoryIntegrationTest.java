package io.zenwave360.example.clinicaltool.modules.surveys.infrastructure.jpa;

import io.zenwave360.example.clinicaltool.common.BaseRepositoryIntegrationTest;
import io.zenwave360.example.clinicaltool.modules.surveys.core.domain.*;
import io.zenwave360.example.clinicaltool.modules.surveys.core.outbound.jpa.SurveyAnswersRepository;

import java.util.HashSet;
import java.util.HashMap;
import java.util.List;
import java.time.*;
import java.math.BigDecimal;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import jakarta.persistence.EntityManager;

class SurveyAnswersRepositoryIntegrationTest extends BaseRepositoryIntegrationTest {

    @Autowired
    EntityManager entityManager;

    @Autowired
    SurveyAnswersRepository surveyAnswersRepository;

    @Test
    void findAllTest() {
        var results = surveyAnswersRepository.findAll();
        Assertions.assertFalse(results.isEmpty());
    }


    @Test
    void findByIdTest() {
        var id = 1L;
        var surveyAnswers = surveyAnswersRepository.findById(id).orElseThrow();
        Assertions.assertNotNull(surveyAnswers.getId());
        Assertions.assertNotNull(surveyAnswers.getVersion());
        Assertions.assertNotNull(surveyAnswers.getCreatedBy());
        Assertions.assertNotNull(surveyAnswers.getCreatedDate());
    }

    @Test
    void saveTest() {
        SurveyAnswers surveyAnswers = new SurveyAnswers();
        surveyAnswers.setSurveyId(0L);
        surveyAnswers.setPatientId(0L);
        surveyAnswers.setDate(LocalDate.now());
        surveyAnswers.setLang("");
        surveyAnswers.setAnswers(List.of(new Answer()));



        // Persist aggregate root
        var created = surveyAnswersRepository.save(surveyAnswers);

        // reloading to get relationships persisted by id
        entityManager.flush();
        entityManager.refresh(created);
        Assertions.assertNotNull(created.getId());
        Assertions.assertNotNull(created.getVersion());
        Assertions.assertNotNull(created.getCreatedBy());
        Assertions.assertNotNull(created.getCreatedDate());


    }

    @Test
    void updateTest() {
        var id = 1L;
        var surveyAnswers = surveyAnswersRepository.findById(id).orElseThrow();
        surveyAnswers.setSurveyId(0L);
        surveyAnswers.setPatientId(0L);
        surveyAnswers.setDate(LocalDate.now());
        surveyAnswers.setLang("");
        surveyAnswers.setAnswers(List.of(new Answer()));

        surveyAnswers = surveyAnswersRepository.save(surveyAnswers);
        Assertions.assertEquals(0L, surveyAnswers.getSurveyId());
        Assertions.assertEquals(0L, surveyAnswers.getPatientId());
        Assertions.assertEquals(LocalDate.now(), surveyAnswers.getDate());
        Assertions.assertEquals("", surveyAnswers.getLang());
        Assertions.assertEquals(List.of(new Answer()), surveyAnswers.getAnswers());
    }

    @Test
    void deleteTest() {
        var id = 1L;
        surveyAnswersRepository.deleteById(id);
        var notFound = surveyAnswersRepository.findById(id);
        Assertions.assertFalse(notFound.isPresent());
    }
}
