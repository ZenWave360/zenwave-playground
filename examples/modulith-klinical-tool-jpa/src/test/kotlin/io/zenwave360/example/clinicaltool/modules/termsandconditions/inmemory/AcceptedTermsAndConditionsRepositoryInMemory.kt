package io.zenwave360.example.clinicaltool.modules.termsandconditions.inmemory

import io.zenwave360.example.clinicaltool.modules.termsandconditions.AcceptedTermsAndConditionsRepository
import io.zenwave360.example.clinicaltool.modules.termsandconditions.domain.*
import java.math.*
import java.time.*
import java.util.*

class AcceptedTermsAndConditionsRepositoryInMemory :
    InMemoryJpaRepository<AcceptedTermsAndConditions, Long>(), AcceptedTermsAndConditionsRepository {
    override fun findByUserId(userId: Long): AcceptedTermsAndConditions? {
        return getEntities().values.firstOrNull { e -> isSameValue(userId, readField(e, "userId")) }
    }
}
