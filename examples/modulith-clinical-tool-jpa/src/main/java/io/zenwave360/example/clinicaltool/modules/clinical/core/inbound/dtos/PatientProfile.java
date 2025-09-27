package io.zenwave360.example.clinicaltool.modules.clinical.core.inbound.dtos;

import io.zenwave360.example.clinicaltool.modules.clinical.core.domain.*;
import jakarta.validation.constraints.*;
import java.io.Serializable;
import java.math.*;
import java.time.*;
import java.util.*;

/**
 * PatientProfile.
 */
@lombok.Getter
@lombok.Setter
public class PatientProfile implements Serializable {

    @NotNull
    @Size(max = 100)
    private String name;

    @NotNull
    @Size(max = 100)
    private String surname;

    @Size(max = 100)
    private String surname2;

    @NotNull
    private LocalDate birthDate;

    @NotNull
    private GenderType gender;

    @NotNull
    @Size(max = 20)
    private String phoneNumber;
}
