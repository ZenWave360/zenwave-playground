package io.zenwave360.example.clinicaltool.modules.clinical.infrastructure.jpa;

import io.zenwave360.example.clinicaltool.common.BaseRepositoryIntegrationTest;
import io.zenwave360.example.clinicaltool.modules.clinical.core.domain.*;
import io.zenwave360.example.clinicaltool.modules.clinical.core.outbound.jpa.HospitalRepository;

import java.util.HashSet;
import java.util.HashMap;
import java.util.List;
import java.time.*;
import java.math.BigDecimal;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import jakarta.persistence.EntityManager;

class HospitalRepositoryIntegrationTest extends BaseRepositoryIntegrationTest {

    @Autowired
    EntityManager entityManager;

    @Autowired
    HospitalRepository hospitalRepository;

    @Test
    void findAllTest() {
        var results = hospitalRepository.findAll();
        Assertions.assertFalse(results.isEmpty());
    }


    @Test
    void findByIdTest() {
        var id = 1L;
        var hospital = hospitalRepository.findById(id).orElseThrow();
        Assertions.assertNotNull(hospital.getId());
        Assertions.assertNotNull(hospital.getVersion());
    }

    @Test
    void saveTest() {
        Hospital hospital = new Hospital();
        hospital.setName("");
        hospital.setLang("");
        hospital.setTimezone("");



        // Persist aggregate root
        var created = hospitalRepository.save(hospital);

        // reloading to get relationships persisted by id
        entityManager.flush();
        entityManager.refresh(created);
        Assertions.assertNotNull(created.getId());
        Assertions.assertNotNull(created.getVersion());


    }

    @Test
    void updateTest() {
        var id = 1L;
        var hospital = hospitalRepository.findById(id).orElseThrow();
        hospital.setName("");
        hospital.setLang("");
        hospital.setTimezone("");

        hospital = hospitalRepository.save(hospital);
        Assertions.assertEquals("", hospital.getName());
        Assertions.assertEquals("", hospital.getLang());
        Assertions.assertEquals("", hospital.getTimezone());
    }

    @Test
    void deleteTest() {
        var id = 1L;
        hospitalRepository.deleteById(id);
        var notFound = hospitalRepository.findById(id);
        Assertions.assertFalse(notFound.isPresent());
    }
}
