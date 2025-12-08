package io.zenwave360.example.clinicaltool.modules.clinical.core.outbound.jpa;

import io.zenwave360.example.clinicaltool.modules.clinical.core.domain.*;
import java.math.*;
import java.time.*;
import java.util.*;

import org.jspecify.annotations.NullMarked;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the Patient entity.
 */
@SuppressWarnings("unused")
@Repository
@NullMarked
public interface PatientRepository extends JpaRepository<Patient, Long> {

    @Override
    @EntityGraph(attributePaths = {"medicalContacts", "personalContacts", "patientAddresses", "hospitalAddresses", "patientWearables"})
    Optional<Patient> findById(Long id);

    @EntityGraph(attributePaths = {"medicalContacts", "personalContacts", "patientAddresses", "hospitalAddresses", "patientWearables"})
    java.util.Optional<Patient> findByPhoneNumberAndHisNumber(String phoneNumber, String hisNumber);
}
