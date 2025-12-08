package io.zenwave360.example.clinicaltool.modules.termsandconditions.dtos

import io.zenwave360.example.clinicaltool.modules.termsandconditions.domain.*
import jakarta.validation.constraints.*
import java.io.Serializable
import java.math.*
import java.time.*

/** AcceptedTermsAndConditionsInput. */
data class AcceptedTermsAndConditionsInput(
    @NotNull val userId: Long? = null,
    @NotNull val termsAndConditionsId: Long? = null,
) : Serializable {}
