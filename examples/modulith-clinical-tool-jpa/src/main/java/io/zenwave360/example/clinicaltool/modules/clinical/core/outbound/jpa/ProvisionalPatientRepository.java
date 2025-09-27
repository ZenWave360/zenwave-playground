package io.zenwave360.example.clinicaltool.modules.clinical.core.outbound.jpa;

import io.zenwave360.example.clinicaltool.modules.clinical.core.domain.*;
import java.math.*;
import java.time.*;
import java.util.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the ProvisionalPatient entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ProvisionalPatientRepository extends JpaRepository<ProvisionalPatient, Long> {
    java.util.Optional<ProvisionalPatient> findByPhoneNumberAndHisNumber(String phoneNumber, String hisNumber);
}
