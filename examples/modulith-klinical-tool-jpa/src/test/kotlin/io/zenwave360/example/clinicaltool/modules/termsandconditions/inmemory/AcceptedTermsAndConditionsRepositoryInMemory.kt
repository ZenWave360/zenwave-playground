package io.zenwave360.example.clinicaltool.modules.termsandconditions.inmemory

import io.zenwave360.example.clinicaltool.modules.termsandconditions.domain.*
import io.zenwave360.example.clinicaltool.modules.termsandconditions.AcceptedTermsAndConditionsRepository
import java.math.*
import java.time.*
import java.util.*

class AcceptedTermsAndConditionsRepositoryInMemory : InMemoryJpaRepository<AcceptedTermsAndConditions, Long>(), AcceptedTermsAndConditionsRepository {
    override fun findByUserId(userId: Long): java.util.Optional<AcceptedTermsAndConditions> {
        return getEntities().values.stream().filter { e ->
             isSameValue(userId, readField(e, "userId")) 
        }.findFirst()
    }
}
