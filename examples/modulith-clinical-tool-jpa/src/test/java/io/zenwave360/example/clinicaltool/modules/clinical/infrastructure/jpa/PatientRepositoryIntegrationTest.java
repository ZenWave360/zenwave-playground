package io.zenwave360.example.clinicaltool.modules.clinical.infrastructure.jpa;

import io.zenwave360.example.clinicaltool.common.BaseRepositoryIntegrationTest;
import io.zenwave360.example.clinicaltool.modules.clinical.core.domain.*;
import io.zenwave360.example.clinicaltool.modules.clinical.core.outbound.jpa.PatientRepository;
import jakarta.persistence.EntityManager;
import java.time.*;
import java.util.HashSet;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

class PatientRepositoryIntegrationTest extends BaseRepositoryIntegrationTest {

    @Autowired
    EntityManager entityManager;

    @Autowired
    PatientRepository patientRepository;

    @Test
    void findAllTest() {
        var results = patientRepository.findAll();
        Assertions.assertFalse(results.isEmpty());
    }

    @Test
    void findByIdTest() {
        var id = 1L;
        var patient = patientRepository.findById(id).orElseThrow();
        Assertions.assertNotNull(patient.getId());
        Assertions.assertNotNull(patient.getVersion());
        Assertions.assertNotNull(patient.getCreatedBy());
        Assertions.assertNotNull(patient.getCreatedDate());
    }

    @Test
    void saveTest() {
        Patient patient = new Patient();
        patient.setUserId(0L);
        patient.setSpikeUUID("");
        patient.setHospitalId(0L);
        patient.setProfilePictureId(0L);
        patient.setPhoneNumber("");
        patient.setHisNumber("");
        patient.setEmail("");
        patient.setGeneralInfo(new GeneralInfo());
        patient.setHealthInsuranceInfo(new HealthInsuranceInfo());
        patient.setDocumentIds(List.of(0L));

        // OneToMany medicalContacts owner: true
        var medicalContacts = new MedicalContact();
        medicalContacts.setName("");
        medicalContacts.setSurname("");
        medicalContacts.setSurname2("");
        medicalContacts.setHospital("");
        medicalContacts.setArea("");
        medicalContacts.setJobPosition("");
        medicalContacts.setPhoneNumber("");
        medicalContacts.setEmail("");
        patient.setMedicalContacts(new HashSet<>());
        patient.addMedicalContacts(medicalContacts);

        // OneToMany personalContacts owner: true
        var personalContacts = new PersonalContact();
        personalContacts.setName("");
        personalContacts.setSurname("");
        personalContacts.setSurname2("");
        personalContacts.setPhoneNumber("");
        personalContacts.setEmail("");
        personalContacts.setPatientRelationshipType(PatientRelationshipType.values()[0]);
        personalContacts.setEmergencyContact(false);
        patient.setPersonalContacts(new HashSet<>());
        patient.addPersonalContacts(personalContacts);

        // OneToMany patientAddresses owner: true
        var patientAddresses = new PatientAddress();
        patientAddresses.setStreet("");
        patientAddresses.setCity("");
        patientAddresses.setPostalCode("");
        patientAddresses.setCountryCode("");
        patientAddresses.setAdditionalInfo("");
        patientAddresses.setCurrent(false);
        patient.setPatientAddresses(new HashSet<>());
        patient.addPatientAddresses(patientAddresses);

        // OneToMany hospitalAddresses owner: true
        var hospitalAddresses = new HospitalAddress();
        hospitalAddresses.setStreet("");
        hospitalAddresses.setCity("");
        hospitalAddresses.setPostalCode("");
        hospitalAddresses.setCountryCode("");
        hospitalAddresses.setAdditionalInfo("");
        patient.setHospitalAddresses(new HashSet<>());
        patient.addHospitalAddresses(hospitalAddresses);

        // OneToMany patientWearables owner: true
        var patientWearables = new PatientWearable();
        patientWearables.setWearableType("");
        patientWearables.setSerialNumber("");
        patient.setPatientWearables(new HashSet<>());
        patient.addPatientWearables(patientWearables);

        // Persist aggregate root
        var created = patientRepository.save(patient);

        // reloading to get relationships persisted by id
        entityManager.flush();
        entityManager.refresh(created);
        Assertions.assertNotNull(created.getId());
        Assertions.assertNotNull(created.getVersion());
        Assertions.assertNotNull(created.getCreatedBy());
        Assertions.assertNotNull(created.getCreatedDate());

        Assertions.assertTrue(patient.getMedicalContacts().stream().allMatch(item -> item.getId() != null));
        Assertions.assertTrue(patient.getPersonalContacts().stream().allMatch(item -> item.getId() != null));
        Assertions.assertTrue(patient.getPatientAddresses().stream().allMatch(item -> item.getId() != null));
        Assertions.assertTrue(patient.getHospitalAddresses().stream().allMatch(item -> item.getId() != null));
        Assertions.assertTrue(patient.getPatientWearables().stream().allMatch(item -> item.getId() != null));
    }

    @Test
    void updateTest() {
        var id = 1L;
        var patient = patientRepository.findById(id).orElseThrow();
        patient.setUserId(0L);
        patient.setSpikeUUID("");
        patient.setHospitalId(0L);
        patient.setProfilePictureId(0L);
        patient.setPhoneNumber("");
        patient.setHisNumber("");
        patient.setEmail("");
        patient.setGeneralInfo(new GeneralInfo());
        patient.setHealthInsuranceInfo(new HealthInsuranceInfo());
        patient.setDocumentIds(List.of(0L));

        patient = patientRepository.save(patient);
        Assertions.assertEquals(0L, patient.getUserId());
        Assertions.assertEquals("", patient.getSpikeUUID());
        Assertions.assertEquals(0L, patient.getHospitalId());
        Assertions.assertEquals(0L, patient.getProfilePictureId());
        Assertions.assertEquals("", patient.getPhoneNumber());
        Assertions.assertEquals("", patient.getHisNumber());
        Assertions.assertEquals("", patient.getEmail());
        Assertions.assertEquals(new GeneralInfo(), patient.getGeneralInfo());
        Assertions.assertEquals(new HealthInsuranceInfo(), patient.getHealthInsuranceInfo());
        Assertions.assertEquals(List.of(0L), patient.getDocumentIds());
    }

    @Test
    void deleteTest() {
        var id = 1L;
        patientRepository.deleteById(id);
        var notFound = patientRepository.findById(id);
        Assertions.assertFalse(notFound.isPresent());
    }
}
