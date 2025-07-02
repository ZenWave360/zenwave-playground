package io.zenwave360.example.clinicaltool.modules.termsandconditions.inmemory

import io.zenwave360.example.clinicaltool.modules.termsandconditions.domain.*
import io.zenwave360.example.clinicaltool.modules.termsandconditions.TermsAndConditionsRepository
import java.math.*
import java.time.*
import java.util.*

class TermsAndConditionsRepositoryInMemory : InMemoryJpaRepository<TermsAndConditions, Long>(), TermsAndConditionsRepository {
}
