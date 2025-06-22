package io.zenwave360.example.clinicaltool.modules.clinical.core.outbound.events;

import io.zenwave360.example.clinicaltool.modules.clinical.core.domain.events.*;
import io.zenwave360.example.clinicaltool.modules.clinical.core.domain.*;

public interface EventPublisher {


    void onDoctorCreated(DoctorCreated event);

    void onPatientCreated(PatientCreated event);

}
