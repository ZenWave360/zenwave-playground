package io.zenwave360.example.clinicaltool.modules.clinical.core.domain.events;

import io.zenwave360.example.clinicaltool.modules.clinical.core.domain.*;
import jakarta.validation.constraints.*;
import java.io.Serializable;
import java.math.*;
import java.time.*;
import java.util.*;

/**
 *
 */
@lombok.Getter
@lombok.Setter
@org.springframework.modulith.NamedInterface("events.DoctorCreated")
public class DoctorCreated implements Serializable {

    @java.io.Serial
    private static final long serialVersionUID = 1L;

    private Doctor doctor;
}
