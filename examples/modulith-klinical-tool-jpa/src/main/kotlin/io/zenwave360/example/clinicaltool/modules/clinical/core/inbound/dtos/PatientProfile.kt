package io.zenwave360.example.clinicaltool.modules.clinical.core.inbound.dtos

import io.zenwave360.example.clinicaltool.modules.clinical.core.domain.*
import jakarta.validation.constraints.*
import java.io.Serializable
import java.math.*
import java.time.*

/** PatientProfile. */
data class PatientProfile(
    @NotNull @Size(max = 100) val name: String? = null,
    @NotNull @Size(max = 100) val surname: String? = null,
    @Size(max = 100) val surname2: String? = null,
    @NotNull val birthDate: LocalDate? = null,
    @NotNull val gender: GenderType? = null,
    @NotNull @Size(max = 20) val phoneNumber: String? = null,
) : Serializable {}
