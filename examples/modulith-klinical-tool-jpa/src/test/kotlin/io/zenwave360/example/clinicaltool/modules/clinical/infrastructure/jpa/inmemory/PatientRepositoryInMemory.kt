package io.zenwave360.example.clinicaltool.modules.clinical.infrastructure.jpa.inmemory

import io.zenwave360.example.clinicaltool.modules.clinical.core.domain.*
import io.zenwave360.example.clinicaltool.modules.clinical.core.outbound.jpa.PatientRepository
import java.math.*
import java.time.*
import java.util.*

class PatientRepositoryInMemory : InMemoryJpaRepository<Patient, Long>(), PatientRepository {
    override fun findByPhoneNumberAndHisNumber(phoneNumber: String, hisNumber: String): java.util.Optional<Patient> {
        return getEntities().values.stream().filter { e ->
             isSameValue(phoneNumber, readField(e, "phoneNumber")) && isSameValue(hisNumber, readField(e, "hisNumber")) 
        }.findFirst()
    }
}
