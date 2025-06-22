package io.zenwave360.example.clinicaltool.modules.surveys.infrastructure.jpa;

import io.zenwave360.example.clinicaltool.common.BaseRepositoryIntegrationTest;
import io.zenwave360.example.clinicaltool.modules.surveys.core.domain.*;
import io.zenwave360.example.clinicaltool.modules.surveys.core.outbound.jpa.SurveyRepository;

import java.util.HashSet;
import java.util.HashMap;
import java.util.List;
import java.time.*;
import java.math.BigDecimal;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import jakarta.persistence.EntityManager;

class SurveyRepositoryIntegrationTest extends BaseRepositoryIntegrationTest {

    @Autowired
    EntityManager entityManager;

    @Autowired
    SurveyRepository surveyRepository;

    @Test
    void findAllTest() {
        var results = surveyRepository.findAll();
        Assertions.assertFalse(results.isEmpty());
    }


    @Test
    void findByIdTest() {
        var id = 1L;
        var survey = surveyRepository.findById(id).orElseThrow();
        Assertions.assertNotNull(survey.getId());
        Assertions.assertNotNull(survey.getVersion());
        Assertions.assertNotNull(survey.getCreatedBy());
        Assertions.assertNotNull(survey.getCreatedDate());
    }

    @Test
    void saveTest() {
        Survey survey = new Survey();
        survey.setName("");
        survey.setHospitalId(0L);
        survey.setTitle("");
        survey.setLang("");
        survey.setSections(List.of(new SurveySection()));



        // Persist aggregate root
        var created = surveyRepository.save(survey);

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
        var survey = surveyRepository.findById(id).orElseThrow();
        survey.setName("");
        survey.setHospitalId(0L);
        survey.setTitle("");
        survey.setLang("");
        survey.setSections(List.of(new SurveySection()));

        survey = surveyRepository.save(survey);
        Assertions.assertEquals("", survey.getName());
        Assertions.assertEquals(0L, survey.getHospitalId());
        Assertions.assertEquals("", survey.getTitle());
        Assertions.assertEquals("", survey.getLang());
        Assertions.assertEquals(List.of(new SurveySection()), survey.getSections());
    }

    @Test
    void deleteTest() {
        var id = 1L;
        surveyRepository.deleteById(id);
        var notFound = surveyRepository.findById(id);
        Assertions.assertFalse(notFound.isPresent());
    }
}
