package io.zenwave360.example.clinicaltool.modules.clinical.infrastructure.events;

import io.zenwave360.example.clinicaltool.modules.clinical.core.domain.events.*;
import io.zenwave360.example.clinicaltool.modules.clinical.core.domain.*;
import io.zenwave360.example.clinicaltool.modules.clinical.core.outbound.events.EventPublisher;

import java.util.ArrayList;
import java.util.List;

public class InMemoryEventPublisher implements EventPublisher {

    private final List<Object> events = new ArrayList<>();

    public List<Object> getEvents() {
        return events;
    }
    public void onDoctorCreated(DoctorCreated event) {
        events.add(event);
    }
    public void onPatientCreated(PatientCreated event) {
        events.add(event);
    }
}
