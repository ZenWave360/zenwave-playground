package io.zenwave360.example.clinicaltool.modules.termsandconditions.inmemory;

import io.zenwave360.example.clinicaltool.modules.termsandconditions.TermsAndConditionsRepository;
import io.zenwave360.example.clinicaltool.modules.termsandconditions.domain.*;
import java.math.*;
import java.time.*;
import java.util.*;

public class TermsAndConditionsRepositoryInMemory extends InMemoryJpaRepository<TermsAndConditions>
        implements TermsAndConditionsRepository {}
