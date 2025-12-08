package io.zenwave360.example.clinicaltool.modules.surveys.domain

import jakarta.persistence.*
import jakarta.validation.constraints.*
import java.io.Serializable
import java.math.*
import java.time.*
import java.util.*

/**  */
@Embeddable
data class OptionTranslation(
    @NotNull @Column(name = "lang", nullable = false) var lang: String? = null,
    @NotNull @Column(name = "text", nullable = false) var text: String? = null,
) : Serializable {

    companion object {
        private const val serialVersionUID = 1L
    }

    override fun toString(): String {
        return this::class.java.name + "@" + Integer.toHexString(hashCode())
    }
}
