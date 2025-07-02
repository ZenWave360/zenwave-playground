package io.zenwave360.example.clinicaltool.modules.termsandconditions.dtos

import io.zenwave360.example.clinicaltool.modules.termsandconditions.domain.*
import java.io.Serializable
import java.math.*
import java.time.*
import jakarta.validation.constraints.*

/**
 * AcceptedTermsAndConditionsInput.
 */
data class AcceptedTermsAndConditionsInput(

    @NotNull
    val termsAndConditionsId: Long?  = null

)  : Serializable {



}
