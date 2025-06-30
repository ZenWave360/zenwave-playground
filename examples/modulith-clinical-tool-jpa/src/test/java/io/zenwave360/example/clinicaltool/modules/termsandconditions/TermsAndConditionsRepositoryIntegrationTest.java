package io.zenwave360.example.clinicaltool.modules.termsandconditions;

import io.zenwave360.example.clinicaltool.common.BaseRepositoryIntegrationTest;
import io.zenwave360.example.clinicaltool.modules.termsandconditions.domain.*;
import io.zenwave360.example.clinicaltool.modules.termsandconditions.TermsAndConditionsRepository;

import java.util.HashSet;
import java.util.HashMap;
import java.util.List;
import java.time.*;
import java.math.BigDecimal;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import jakarta.persistence.EntityManager;

class TermsAndConditionsRepositoryIntegrationTest extends BaseRepositoryIntegrationTest {

    @Autowired
    EntityManager entityManager;

    @Autowired
    TermsAndConditionsRepository termsAndConditionsRepository;

    @Test
    void findAllTest() {
        var results = termsAndConditionsRepository.findAll();
        Assertions.assertFalse(results.isEmpty());
    }


    @Test
    void findByIdTest() {
        var id = 1L;
        var termsAndConditions = termsAndConditionsRepository.findById(id).orElseThrow();
        Assertions.assertNotNull(termsAndConditions.getId());
        Assertions.assertNotNull(termsAndConditions.getVersion());
    }

    @Test
    void saveTest() {
        TermsAndConditions termsAndConditions = new TermsAndConditions();
        termsAndConditions.setContent("");
        termsAndConditions.setLang("");
        termsAndConditions.setContentVersion("");
        termsAndConditions.setStartDate(LocalDate.now());



        // Persist aggregate root
        var created = termsAndConditionsRepository.save(termsAndConditions);

        // reloading to get relationships persisted by id
        entityManager.flush();
        entityManager.refresh(created);
        Assertions.assertNotNull(created.getId());
        Assertions.assertNotNull(created.getVersion());


    }

    @Test
    void updateTest() {
        var id = 1L;
        var termsAndConditions = termsAndConditionsRepository.findById(id).orElseThrow();
        termsAndConditions.setContent("");
        termsAndConditions.setLang("");
        termsAndConditions.setContentVersion("");
        termsAndConditions.setStartDate(LocalDate.now());

        termsAndConditions = termsAndConditionsRepository.save(termsAndConditions);
        Assertions.assertEquals("", termsAndConditions.getContent());
        Assertions.assertEquals("", termsAndConditions.getLang());
        Assertions.assertEquals("", termsAndConditions.getContentVersion());
        Assertions.assertEquals(LocalDate.now(), termsAndConditions.getStartDate());
    }

    @Test
    void deleteTest() {
        var id = 1L;
        termsAndConditionsRepository.deleteById(id);
        var notFound = termsAndConditionsRepository.findById(id);
        Assertions.assertFalse(notFound.isPresent());
    }
}
