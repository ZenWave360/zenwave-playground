package io.zenwave360.example.clinicaltool.modules.surveys.service.dtos

import io.zenwave360.example.clinicaltool.modules.surveys.domain.*
import jakarta.validation.constraints.*
import java.io.Serializable
import java.math.*
import java.time.*

/** SurveyAndQuestions. */
data class SurveyAndQuestions(
    @NotNull val surveyId: Long? = null,
    @NotNull @Size(max = 254) val name: String? = null,
    @NotNull @Size(max = 254) val title: String? = null,
    @NotNull val hospitalId: Long? = null,
    @NotNull @Size(max = 3) val lang: String? = null,
    val sections: MutableList<SurveySectionOutput> = mutableListOf(),
) : Serializable {

    fun addSections(sections: SurveySectionOutput): SurveyAndQuestions {
        this.sections += sections
        return this
    }
}
