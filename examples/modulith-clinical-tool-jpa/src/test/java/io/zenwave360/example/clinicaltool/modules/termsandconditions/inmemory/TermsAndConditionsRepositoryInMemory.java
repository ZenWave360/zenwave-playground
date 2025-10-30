package io.zenwave360.example.clinicaltool.modules.termsandconditions.inmemory;

import io.zenwave360.example.clinicaltool.modules.termsandconditions.TermsAndConditionsRepository;
import io.zenwave360.example.clinicaltool.modules.termsandconditions.domain.*;
import java.math.*;
import java.time.*;
import java.util.*;

public class TermsAndConditionsRepositoryInMemory extends InMemoryJpaRepository<TermsAndConditions>
        implements TermsAndConditionsRepository {

    @Override public TermsAndConditions findOneByLangAndStartDateAfterOrderByStartDateAsc(
            String lang, LocalDate localDate) {
        return getEntities().values().stream()
                .filter(e -> isSameValue(lang, readField(e, "lang"))
                        && isSameValue(localDate, readField(e, "startDate"))) // FIXME
                .findFirst()
                .orElse(null);
    }
}
