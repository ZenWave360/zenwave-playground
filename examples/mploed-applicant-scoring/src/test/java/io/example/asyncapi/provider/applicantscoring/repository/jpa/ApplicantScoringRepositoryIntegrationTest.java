package io.example.asyncapi.provider.applicantscoring.repository.jpa;

import io.zenwave360.example.applicantscoring.domain.*;
import io.zenwave360.example.applicantscoring.repository.jpa.ApplicantScoringRepository;
import io.zenwave360.example.applicantscoring.repository.jpa.BaseRepositoryIntegrationTest;
import jakarta.persistence.EntityManager;
import java.time.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

class ApplicantScoringRepositoryIntegrationTest extends BaseRepositoryIntegrationTest {

    @Autowired
    EntityManager entityManager;

    @Autowired
    ApplicantScoringRepository applicantScoringRepository;

    @Test
    void findAllTest() {
        var results = applicantScoringRepository.findAll();
        Assertions.assertFalse(results.isEmpty());
    }

    @Test
    void findByIdTest() {
        var id = 1L;
        var applicantScoring = applicantScoringRepository.findById(id).orElseThrow();
        Assertions.assertNotNull(applicantScoring.getId());
        Assertions.assertNotNull(applicantScoring.getVersion());
        Assertions.assertNotNull(applicantScoring.getCreatedBy());
        Assertions.assertNotNull(applicantScoring.getCreatedDate());
    }

    @Test
    void saveTest() {
        ApplicantScoring applicantScoring = new ApplicantScoring();
        applicantScoring.setApplicationNumber(new ApplicationNumber());
        applicantScoring.setCity(new City());
        applicantScoring.setBalanceAtBank(new BalanceAtBank());

        // Persist aggregate root
        var created = applicantScoringRepository.save(applicantScoring);

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
        var applicantScoring = applicantScoringRepository.findById(id).orElseThrow();
        applicantScoring.setApplicationNumber(new ApplicationNumber());
        applicantScoring.setCity(new City());
        applicantScoring.setBalanceAtBank(new BalanceAtBank());

        applicantScoring = applicantScoringRepository.save(applicantScoring);
        Assertions.assertEquals(new ApplicationNumber(), applicantScoring.getApplicationNumber());
        Assertions.assertEquals(new City(), applicantScoring.getCity());
        Assertions.assertEquals(new BalanceAtBank(), applicantScoring.getBalanceAtBank());
    }

    @Test
    void deleteTest() {
        var id = 1L;
        applicantScoringRepository.deleteById(id);
        var notFound = applicantScoringRepository.findById(id);
        Assertions.assertFalse(notFound.isPresent());
    }
}
