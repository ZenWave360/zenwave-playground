package io.zenwave360.example.clinicaltool.modules.surveys.domain

import jakarta.persistence.*
import jakarta.validation.constraints.*
import java.io.Serializable
import java.math.*
import java.time.*
import java.util.*

/**  */
@Embeddable
data class SectionTranslation(
    @NotNull @Column(name = "lang", nullable = false) var lang: String? = null,
    @NotNull @Column(name = "title", nullable = false) var title: String? = null,
    @Column(name = "summary") var summary: String? = null,
) : Serializable {

    companion object {
        private const val serialVersionUID = 1L
    }

    override fun toString(): String {
        return this::class.java.name + "@" + Integer.toHexString(hashCode())
    }
}
