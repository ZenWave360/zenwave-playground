package io.zenwave360.example.clinicaltool.modules.termsandconditions;

import io.zenwave360.example.clinicaltool.modules.termsandconditions.domain.*;

import java.time.*;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the TermsAndConditions entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TermsAndConditionsRepository extends JpaRepository<TermsAndConditions, Long> {
    TermsAndConditions findOneByLangAndStartDateBeforeOrderByStartDateAsc(String lang, LocalDate localDate);
}
