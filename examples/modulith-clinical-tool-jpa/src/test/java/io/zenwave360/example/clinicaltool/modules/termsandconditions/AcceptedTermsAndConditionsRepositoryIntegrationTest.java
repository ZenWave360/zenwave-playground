package io.zenwave360.example.clinicaltool.modules.termsandconditions;

import io.zenwave360.example.clinicaltool.common.BaseRepositoryIntegrationTest;
import io.zenwave360.example.clinicaltool.modules.termsandconditions.domain.*;
import jakarta.persistence.EntityManager;
import java.time.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

class AcceptedTermsAndConditionsRepositoryIntegrationTest extends BaseRepositoryIntegrationTest {

    @Autowired
    EntityManager entityManager;

    @Autowired
    AcceptedTermsAndConditionsRepository acceptedTermsAndConditionsRepository;

    @Test
    void findAllTest() {
        var results = acceptedTermsAndConditionsRepository.findAll();
        Assertions.assertFalse(results.isEmpty());
    }

    @Test
    void findByIdTest() {
        var id = 1L;
        var acceptedTermsAndConditions =
                acceptedTermsAndConditionsRepository.findById(id).orElseThrow();
        Assertions.assertNotNull(acceptedTermsAndConditions.getId());
        Assertions.assertNotNull(acceptedTermsAndConditions.getVersion());
    }

    @Test
    void saveTest() {
        AcceptedTermsAndConditions acceptedTermsAndConditions = new AcceptedTermsAndConditions();
        acceptedTermsAndConditions.setUserId(0L);
        acceptedTermsAndConditions.setTermsAndConditionsId(0L);
        acceptedTermsAndConditions.setAcceptedDate(Instant.now());

        // Persist aggregate root
        var created = acceptedTermsAndConditionsRepository.save(acceptedTermsAndConditions);

        // reloading to get relationships persisted by id
        entityManager.flush();
        entityManager.refresh(created);
        Assertions.assertNotNull(created.getId());
        Assertions.assertNotNull(created.getVersion());
    }

    @Test
    void updateTest() {
        var id = 1L;
        var acceptedTermsAndConditions =
                acceptedTermsAndConditionsRepository.findById(id).orElseThrow();
        acceptedTermsAndConditions.setUserId(0L);
        acceptedTermsAndConditions.setTermsAndConditionsId(0L);
        acceptedTermsAndConditions.setAcceptedDate(Instant.now());

        acceptedTermsAndConditions = acceptedTermsAndConditionsRepository.save(acceptedTermsAndConditions);
        Assertions.assertEquals(0L, acceptedTermsAndConditions.getUserId());
        Assertions.assertEquals(0L, acceptedTermsAndConditions.getTermsAndConditionsId());
        Assertions.assertEquals(Instant.now(), acceptedTermsAndConditions.getAcceptedDate());
    }

    @Test
    void deleteTest() {
        var id = 1L;
        acceptedTermsAndConditionsRepository.deleteById(id);
        var notFound = acceptedTermsAndConditionsRepository.findById(id);
        Assertions.assertFalse(notFound.isPresent());
    }
}
