package io.zenwave360.example.clinicaltool.modules.termsandconditions.infrastructure.jpa.inmemory;

import io.zenwave360.example.clinicaltool.modules.termsandconditions.core.domain.*;
import io.zenwave360.example.clinicaltool.modules.termsandconditions.core.outbound.jpa.TermsAndConditionsRepository;
import java.math.*;
import java.time.*;
import java.util.*;

public class TermsAndConditionsRepositoryInMemory extends InMemoryJpaRepository<TermsAndConditions> implements TermsAndConditionsRepository {
}
