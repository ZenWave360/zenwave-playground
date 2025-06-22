package io.zenwave360.example.clinicaltool.modules.termsandconditions.infrastructure.jpa.inmemory;

import io.zenwave360.example.clinicaltool.modules.termsandconditions.core.domain.*;
import io.zenwave360.example.clinicaltool.modules.termsandconditions.core.outbound.jpa.AcceptedTermsAndConditionsRepository;
import java.math.*;
import java.time.*;
import java.util.*;

public class AcceptedTermsAndConditionsRepositoryInMemory extends InMemoryJpaRepository<AcceptedTermsAndConditions> implements AcceptedTermsAndConditionsRepository {
    @Override
    public java.util.Optional<AcceptedTermsAndConditions> findByUserId(Long userId) {
        return getEntities().values().stream().filter(e ->
             isSameValue(userId, readField(e, "userId")) 
        ).findFirst();
    }
}
