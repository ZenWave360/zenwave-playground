package io.zenwave360.example.clinicaltool.modules.clinical.infrastructure.events

import io.zenwave360.example.clinicaltool.modules.clinical.core.domain.events.*
import io.zenwave360.example.clinicaltool.modules.clinical.core.domain.*
import io.zenwave360.example.clinicaltool.modules.clinical.core.outbound.events.EventPublisher
import org.springframework.context.ApplicationEventPublisher
import org.springframework.stereotype.Component

@Component
class DefaultEventPublisher(
    private val applicationEventPublisher: ApplicationEventPublisher
) : EventPublisher {


    override fun onDoctorCreated(event: DoctorCreated) {
        applicationEventPublisher.publishEvent(event)
    }

    override fun onPatientCreated(event: PatientCreated) {
        applicationEventPublisher.publishEvent(event)
    }

}
