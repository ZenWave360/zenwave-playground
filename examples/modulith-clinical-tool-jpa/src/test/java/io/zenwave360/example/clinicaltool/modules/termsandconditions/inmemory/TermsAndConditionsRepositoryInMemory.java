package io.zenwave360.example.clinicaltool.modules.termsandconditions.inmemory;

import io.zenwave360.example.clinicaltool.modules.termsandconditions.TermsAndConditionsRepository;
import io.zenwave360.example.clinicaltool.modules.termsandconditions.domain.*;
import java.math.*;
import java.time.*;
import java.util.*;

public class TermsAndConditionsRepositoryInMemory extends InMemoryJpaRepository<TermsAndConditions>
        implements TermsAndConditionsRepository {

    @Override
    public TermsAndConditions findOneByLangAndStartDateBeforeOrderByStartDateAsc(String lang, LocalDate localDate) {
        return getEntities().values().stream()
                .filter(e -> isSameValue(lang, readField(e, "lang"))
                        && ((LocalDate) readField(e, "startDate")).isBefore(localDate))
                .sorted((e1, e2) ->
                        ((LocalDate) readField(e1, "startDate")).compareTo((LocalDate) readField(e2, "startDate")))
                .findFirst()
                .orElse(null);
    }
}
