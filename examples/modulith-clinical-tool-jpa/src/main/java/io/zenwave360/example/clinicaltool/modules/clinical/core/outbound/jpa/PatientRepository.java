package io.zenwave360.example.clinicaltool.modules.clinical.core.outbound.jpa;

import io.zenwave360.example.clinicaltool.modules.clinical.core.domain.*;
import java.math.*;
import java.time.*;
import java.util.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the Patient entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {
    java.util.Optional<Patient> findByPhoneNumberAndHisNumber(String phoneNumber, String hisNumber);
}
