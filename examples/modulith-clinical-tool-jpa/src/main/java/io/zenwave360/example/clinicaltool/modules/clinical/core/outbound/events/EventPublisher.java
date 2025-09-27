package io.zenwave360.example.clinicaltool.modules.clinical.core.outbound.events;

import io.zenwave360.example.clinicaltool.modules.clinical.core.domain.*;
import io.zenwave360.example.clinicaltool.modules.clinical.core.domain.events.*;

public interface EventPublisher {

    void onDoctorCreated(DoctorCreated event);

    void onPatientCreated(PatientCreated event);
}
