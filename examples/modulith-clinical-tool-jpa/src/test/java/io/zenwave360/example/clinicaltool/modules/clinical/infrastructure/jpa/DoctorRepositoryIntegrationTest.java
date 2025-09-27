package io.zenwave360.example.clinicaltool.modules.clinical.infrastructure.jpa;

import io.zenwave360.example.clinicaltool.common.BaseRepositoryIntegrationTest;
import io.zenwave360.example.clinicaltool.modules.clinical.core.domain.*;
import io.zenwave360.example.clinicaltool.modules.clinical.core.outbound.jpa.DoctorRepository;
import jakarta.persistence.EntityManager;
import java.time.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

class DoctorRepositoryIntegrationTest extends BaseRepositoryIntegrationTest {

    @Autowired
    EntityManager entityManager;

    @Autowired
    DoctorRepository doctorRepository;

    @Test
    void findAllTest() {
        var results = doctorRepository.findAll();
        Assertions.assertFalse(results.isEmpty());
    }

    @Test
    void findByIdTest() {
        var id = 1L;
        var doctor = doctorRepository.findById(id).orElseThrow();
        Assertions.assertNotNull(doctor.getId());
        Assertions.assertNotNull(doctor.getVersion());
        Assertions.assertNotNull(doctor.getCreatedBy());
        Assertions.assertNotNull(doctor.getCreatedDate());
    }

    @Test
    void saveTest() {
        Doctor doctor = new Doctor();
        doctor.setUserId(0L);
        doctor.setProfilePictureId(0L);
        doctor.setHospitalId(0L);
        doctor.setName("");
        doctor.setSurname("");
        doctor.setSurname2("");
        doctor.setEmail("");
        doctor.setPhoneNumber("");
        doctor.setLang("");

        // Persist aggregate root
        var created = doctorRepository.save(doctor);

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
        var doctor = doctorRepository.findById(id).orElseThrow();
        doctor.setUserId(0L);
        doctor.setProfilePictureId(0L);
        doctor.setHospitalId(0L);
        doctor.setName("");
        doctor.setSurname("");
        doctor.setSurname2("");
        doctor.setEmail("");
        doctor.setPhoneNumber("");
        doctor.setLang("");

        doctor = doctorRepository.save(doctor);
        Assertions.assertEquals(0L, doctor.getUserId());
        Assertions.assertEquals(0L, doctor.getProfilePictureId());
        Assertions.assertEquals(0L, doctor.getHospitalId());
        Assertions.assertEquals("", doctor.getName());
        Assertions.assertEquals("", doctor.getSurname());
        Assertions.assertEquals("", doctor.getSurname2());
        Assertions.assertEquals("", doctor.getEmail());
        Assertions.assertEquals("", doctor.getPhoneNumber());
        Assertions.assertEquals("", doctor.getLang());
    }

    @Test
    void deleteTest() {
        var id = 1L;
        doctorRepository.deleteById(id);
        var notFound = doctorRepository.findById(id);
        Assertions.assertFalse(notFound.isPresent());
    }
}
