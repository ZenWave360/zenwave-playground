package io.zenwave360.example.clinicaltool.modules.surveys.service.dtos

import io.zenwave360.example.clinicaltool.modules.surveys.domain.*
import java.io.Serializable
import java.math.*
import java.time.*
import jakarta.validation.constraints.*

/**
 * SurveySectionOutput.
 */
data class SurveySectionOutput(

    @NotNull @Size(max = 254)
    val name: String?  = null,

    @NotNull @Size(max = 254)
    val title: String?  = null,

    
    val summary: String?  = null,

    
    val questions: MutableList<QuestionTranslationOutput> = mutableListOf()

)  : Serializable {





    fun addQuestions(questions: QuestionTranslationOutput): SurveySectionOutput {
        this.questions += questions
        return this
    }

}
