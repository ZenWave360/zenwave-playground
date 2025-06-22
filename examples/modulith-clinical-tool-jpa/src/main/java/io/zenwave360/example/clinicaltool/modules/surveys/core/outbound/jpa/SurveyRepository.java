package io.zenwave360.example.clinicaltool.modules.surveys.core.outbound.jpa;

import io.zenwave360.example.clinicaltool.modules.surveys.core.domain.*;
import java.math.*;
import java.time.*;
import java.util.*;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the Survey entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SurveyRepository extends JpaRepository<Survey, Long> {java.util.Optional<Survey> findByNameAndHospitalId(String name, Long hospitalId);
}
