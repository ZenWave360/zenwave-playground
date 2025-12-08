package io.zenwave360.example.clinicaltool.modules.surveys.domain

import jakarta.persistence.*
import jakarta.validation.constraints.*
import java.io.Serializable
import java.math.*
import java.time.*
import java.util.*

/**  */
// @Embeddable // json embedded
data class SurveySection(

    /** Unique identifier for the section */
    @NotNull @Size(max = 254) @Column(name = "name", nullable = false, length = 254) var name: String? = null,
    @Embedded
    @AttributeOverride(name = "lang", column = Column(name = "translations_lang"))
    @AttributeOverride(name = "title", column = Column(name = "translations_title"))
    @AttributeOverride(name = "summary", column = Column(name = "translations_summary"))
    var translations: MutableList<SectionTranslation> = mutableListOf(),

    /** Sorted List of Questions for this section */
    @Column(name = "question_ids") var questionIds: MutableList<Long> = mutableListOf(),
) : Serializable {

    companion object {
        private const val serialVersionUID = 1L
    }

    override fun toString(): String {
        return this::class.java.name + "@" + Integer.toHexString(hashCode())
    }
}
