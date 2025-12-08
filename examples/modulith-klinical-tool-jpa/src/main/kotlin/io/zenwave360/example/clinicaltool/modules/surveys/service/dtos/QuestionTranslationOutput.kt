package io.zenwave360.example.clinicaltool.modules.surveys.service.dtos

import io.zenwave360.example.clinicaltool.modules.surveys.domain.*
import jakarta.validation.constraints.*
import java.io.Serializable
import java.math.*
import java.time.*

/** QuestionTranslationOutput. */
data class QuestionTranslationOutput(
    @NotNull val questionId: Long? = null,
    val required: Boolean? = true,
    val rangeStart: Integer? = null,
    val rangeEnd: Integer? = null,
    @NotNull @Size(max = 254) val text: String? = null,
    @NotNull val questionType: QuestionType? = null,
    val options: MutableList<OptionTranslationOutput> = mutableListOf(),
    val includeOther: Boolean? = false,
) : Serializable {

    fun addOptions(options: OptionTranslationOutput): QuestionTranslationOutput {
        this.options += options
        return this
    }
}
