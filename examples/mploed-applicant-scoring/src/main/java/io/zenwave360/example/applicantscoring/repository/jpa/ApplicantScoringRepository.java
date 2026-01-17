package io.zenwave360.example.applicantscoring.repository.jpa;

import io.zenwave360.example.applicantscoring.domain.*;
import java.math.*;
import java.time.*;
import java.util.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the ApplicantScoring entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ApplicantScoringRepository extends JpaRepository<ApplicantScoring, Long> {}
