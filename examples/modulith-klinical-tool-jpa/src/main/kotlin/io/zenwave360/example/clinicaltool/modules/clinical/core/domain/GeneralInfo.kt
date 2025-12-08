package io.zenwave360.example.clinicaltool.modules.clinical.core.domain

import jakarta.persistence.*
import jakarta.validation.constraints.*
import java.io.Serializable
import java.math.*
import java.time.*
import java.util.*

/**  */
@Embeddable
data class GeneralInfo(
    @NotNull @Size(max = 100) @Column(name = "name", nullable = false, length = 100) var name: String? = null,
    @NotNull @Size(max = 100) @Column(name = "surname", nullable = false, length = 100) var surname: String? = null,
    @Size(max = 100) @Column(name = "surname2", length = 100) var surname2: String? = null,
    @NotNull
    @Column(name = "identity_document_type", nullable = false)
    @Convert(converter = IdentityDocumentType.IdentityDocumentTypeConverter::class)
    var identityDocumentType: IdentityDocumentType? = null,
    @NotNull
    @Size(max = 20)
    @Column(name = "identity_document_number", nullable = false, length = 20)
    var identityDocumentNumber: String? = null,
    @NotNull @Column(name = "birth_date", nullable = false) var birthDate: LocalDate? = null,
    @NotNull
    @Column(name = "gender", nullable = false)
    @Convert(converter = GenderType.GenderTypeConverter::class)
    var gender: GenderType? = null,

    /** Primary language of the patient */
    @Size(max = 3) @Column(name = "lang", length = 3) var lang: String? = null,
) : Serializable {

    companion object {
        private const val serialVersionUID = 1L
    }

    override fun toString(): String {
        return this::class.java.name + "@" + Integer.toHexString(hashCode())
    }
}
