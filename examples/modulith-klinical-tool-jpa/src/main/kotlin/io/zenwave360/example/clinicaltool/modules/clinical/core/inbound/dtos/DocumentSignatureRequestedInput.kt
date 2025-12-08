package io.zenwave360.example.clinicaltool.modules.clinical.core.inbound.dtos

import io.zenwave360.example.clinicaltool.modules.clinical.core.domain.*
import jakarta.validation.constraints.*
import java.io.Serializable
import java.math.*
import java.time.*

/** DocumentSignatureRequestedInput. */
data class DocumentSignatureRequestedInput(
    @NotNull val documentInfoId: Long? = null,
    @NotNull val patientId: Long? = null,
) : Serializable {}
