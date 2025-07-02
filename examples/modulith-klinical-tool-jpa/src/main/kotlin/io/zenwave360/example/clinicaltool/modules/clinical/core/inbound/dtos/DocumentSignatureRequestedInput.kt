package io.zenwave360.example.clinicaltool.modules.clinical.core.inbound.dtos

import io.zenwave360.example.clinicaltool.modules.clinical.core.domain.*
import java.io.Serializable
import java.math.*
import java.time.*
import jakarta.validation.constraints.*

/**
 * DocumentSignatureRequestedInput.
 */
data class DocumentSignatureRequestedInput(

    @NotNull
    val documentInfoId: Long?  = null,

    @NotNull
    val patientId: Long?  = null

)  : Serializable {




}
