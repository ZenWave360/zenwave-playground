package io.zenwave360.example.clinicaltool.modules.surveys.service.dtos

import io.zenwave360.example.clinicaltool.modules.surveys.domain.*
import java.io.Serializable
import java.math.*
import java.time.*
import jakarta.validation.constraints.*

/**
 * OptionTranslationOutput.
 */
data class OptionTranslationOutput(

    @NotNull @Size(max = 254)
    val name: String?  = null,

    @NotNull
    val text: String?  = null

)  : Serializable {




}
