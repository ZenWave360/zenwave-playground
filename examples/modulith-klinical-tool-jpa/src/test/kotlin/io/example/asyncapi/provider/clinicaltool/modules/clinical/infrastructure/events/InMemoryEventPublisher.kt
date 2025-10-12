package io.example.asyncapi.provider.clinicaltool.modules.clinical.infrastructure.events

import io.zenwave360.example.clinicaltool.modules.clinical.core.domain.events.*
import io.zenwave360.example.clinicaltool.modules.clinical.core.domain.*
import io.zenwave360.example.clinicaltool.modules.clinical.core.outbound.events.EventPublisher

class InMemoryEventPublisher : EventPublisher {

    private val events = mutableListOf<Any>()

    fun getEvents(): MutableList<Any> {
        return events
    }
    override fun onDoctorCreated(event: DoctorCreated) {
        events.add(event)
    }
    override fun onPatientCreated(event: PatientCreated) {
        events.add(event)
    }
}
