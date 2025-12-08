package io.zenwave360.example.clinicaltool.modules.surveys.service.dtos

import io.zenwave360.example.clinicaltool.modules.surveys.domain.*
import jakarta.validation.constraints.*
import java.io.Serializable
import java.math.*
import java.time.*

/** SurveysByHospital. */
data class SurveysByHospital(@NotNull val hospitalId: Long? = null, @Size(max = 3) val lang: String? = null) :
    Serializable {}
