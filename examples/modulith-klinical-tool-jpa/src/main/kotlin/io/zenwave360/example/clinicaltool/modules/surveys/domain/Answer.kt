package io.zenwave360.example.clinicaltool.modules.surveys.domain

import jakarta.persistence.*
import jakarta.validation.constraints.*
import java.io.Serializable
import java.math.*
import java.time.*
import java.util.*

/**  */
// @Embeddable // json embedded
data class Answer(
    @NotNull @Column(name = "question_id", nullable = false) var questionId: Long? = null,
    @Column(name = "value") var value: String? = null,
    @Column(name = "values") var values: MutableList<String> = mutableListOf(),
    @Column(name = "other_value") var otherValue: String? = null,
) : Serializable {

    companion object {
        private const val serialVersionUID = 1L
    }

    override fun toString(): String {
        return this::class.java.name + "@" + Integer.toHexString(hashCode())
    }
}
