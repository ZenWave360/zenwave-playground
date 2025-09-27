package io.zenwave360.example.clinicaltool.modules.clinical.infrastructure.events;

import io.zenwave360.example.clinicaltool.modules.clinical.core.domain.*;
import io.zenwave360.example.clinicaltool.modules.clinical.core.domain.events.*;
import io.zenwave360.example.clinicaltool.modules.clinical.core.outbound.events.EventPublisher;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
@lombok.RequiredArgsConstructor
public class DefaultEventPublisher implements EventPublisher {

    private final ApplicationEventPublisher applicationEventPublisher;

    public void onDoctorCreated(DoctorCreated event) {
        applicationEventPublisher.publishEvent(event);
    }

    public void onPatientCreated(PatientCreated event) {
        applicationEventPublisher.publishEvent(event);
    }
}
