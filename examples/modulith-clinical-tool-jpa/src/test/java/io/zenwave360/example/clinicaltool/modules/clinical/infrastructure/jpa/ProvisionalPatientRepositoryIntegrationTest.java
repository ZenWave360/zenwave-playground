package io.zenwave360.example.clinicaltool.modules.clinical.infrastructure.jpa;

import io.zenwave360.example.clinicaltool.common.BaseRepositoryIntegrationTest;
import io.zenwave360.example.clinicaltool.modules.clinical.core.domain.*;
import io.zenwave360.example.clinicaltool.modules.clinical.core.outbound.jpa.ProvisionalPatientRepository;

import java.util.HashSet;
import java.util.HashMap;
import java.util.List;
import java.time.*;
import java.math.BigDecimal;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import jakarta.persistence.EntityManager;

class ProvisionalPatientRepositoryIntegrationTest extends BaseRepositoryIntegrationTest {

    @Autowired
    EntityManager entityManager;

    @Autowired
    ProvisionalPatientRepository provisionalPatientRepository;

    @Test
    void findAllTest() {
        var results = provisionalPatientRepository.findAll();
        Assertions.assertFalse(results.isEmpty());
    }


    @Test
    void findByIdTest() {
        var id = 1L;
        var provisionalPatient = provisionalPatientRepository.findById(id).orElseThrow();
        Assertions.assertNotNull(provisionalPatient.getId());
        Assertions.assertNotNull(provisionalPatient.getVersion());
    }

    @Test
    void saveTest() {
        ProvisionalPatient provisionalPatient = new ProvisionalPatient();
        provisionalPatient.setPhoneNumber("");
        provisionalPatient.setHisNumber("");
        provisionalPatient.setPatient(new Patient());



        // Persist aggregate root
        var created = provisionalPatientRepository.save(provisionalPatient);

        // reloading to get relationships persisted by id
        entityManager.flush();
        entityManager.refresh(created);
        Assertions.assertNotNull(created.getId());
        Assertions.assertNotNull(created.getVersion());


    }

    @Test
    void updateTest() {
        var id = 1L;
        var provisionalPatient = provisionalPatientRepository.findById(id).orElseThrow();
        provisionalPatient.setPhoneNumber("");
        provisionalPatient.setHisNumber("");
        provisionalPatient.setPatient(new Patient());

        provisionalPatient = provisionalPatientRepository.save(provisionalPatient);
        Assertions.assertEquals("", provisionalPatient.getPhoneNumber());
        Assertions.assertEquals("", provisionalPatient.getHisNumber());
        Assertions.assertEquals(new Patient(), provisionalPatient.getPatient());
    }

    @Test
    void deleteTest() {
        var id = 1L;
        provisionalPatientRepository.deleteById(id);
        var notFound = provisionalPatientRepository.findById(id);
        Assertions.assertFalse(notFound.isPresent());
    }
}
