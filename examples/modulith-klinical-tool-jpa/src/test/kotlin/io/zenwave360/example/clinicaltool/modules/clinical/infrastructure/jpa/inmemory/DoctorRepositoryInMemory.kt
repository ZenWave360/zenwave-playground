package io.zenwave360.example.clinicaltool.modules.clinical.infrastructure.jpa.inmemory

import io.zenwave360.example.clinicaltool.modules.clinical.core.domain.*
import io.zenwave360.example.clinicaltool.modules.clinical.core.outbound.jpa.DoctorRepository
import java.math.*
import java.time.*
import java.util.*

class DoctorRepositoryInMemory : InMemoryJpaRepository<Doctor, Long>(), DoctorRepository {
}
