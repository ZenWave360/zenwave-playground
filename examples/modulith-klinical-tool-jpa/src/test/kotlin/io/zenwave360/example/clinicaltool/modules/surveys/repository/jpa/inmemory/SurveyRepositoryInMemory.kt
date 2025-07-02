package io.zenwave360.example.clinicaltool.modules.surveys.repository.jpa.inmemory

import io.zenwave360.example.clinicaltool.modules.surveys.domain.*
import io.zenwave360.example.clinicaltool.modules.surveys.repository.jpa.SurveyRepository
import java.math.*
import java.time.*
import java.util.*

class SurveyRepositoryInMemory : InMemoryJpaRepository<Survey, Long>(), SurveyRepository {
    override fun findByNameAndHospitalId(name: String, hospitalId: Long): java.util.Optional<Survey> {
        return getEntities().values.stream().filter { e ->
             isSameValue(name, readField(e, "name")) && isSameValue(hospitalId, readField(e, "hospitalId")) 
        }.findFirst()
    }
}
