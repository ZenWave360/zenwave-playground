package io.zenwave360.example.clinicaltool.modules.clinical.core.outbound.events

import io.zenwave360.example.clinicaltool.modules.clinical.core.domain.*
import io.zenwave360.example.clinicaltool.modules.clinical.core.domain.events.*

interface EventPublisher {

    fun onDoctorCreated(event: DoctorCreated)

    fun onPatientCreated(event: PatientCreated)
}
