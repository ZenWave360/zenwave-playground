package io.zenwave360.example.clinicaltool.modules.clinical.core.domain

import java.io.Serializable
import java.math.*
import java.time.*
import java.util.*
import jakarta.persistence.*
import jakarta.validation.constraints.*
import org.hibernate.annotations.Cache
import org.hibernate.annotations.CacheConcurrencyStrategy

/**
* 
*/
@Embeddable
data class GeneralInfo(
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)

    var id: Long? = null,

    @Version
    var version: Int? = null,

    @NotNull @Size(max = 100)@Column(name = "name", nullable = false, length = 100)
    var name: String?  = null,

    @NotNull @Size(max = 100)@Column(name = "surname", nullable = false, length = 100)
    var surname: String?  = null,

    @Size(max = 100)@Column(name = "surname2", length = 100)
    var surname2: String?  = null,

    @NotNull@Column(name = "identity_document_type", nullable = false)
    @Convert(converter = IdentityDocumentType.IdentityDocumentTypeConverter::class)
    
    var identityDocumentType: IdentityDocumentType?  = null,

    @NotNull @Size(max = 20)@Column(name = "identity_document_number", nullable = false, length = 20)
    var identityDocumentNumber: String?  = null,

    @NotNull@Column(name = "birth_date", nullable = false)
    var birthDate: LocalDate?  = null,

    @NotNull@Column(name = "gender", nullable = false)
    @Convert(converter = GenderType.GenderTypeConverter::class)
    
    var gender: GenderType?  = null,

    /**
    * Primary language of the patient
    */
    @Size(max = 3)@Column(name = "lang", length = 3)
    var lang: String?  = null



)  : Serializable {

    companion object {
        private const val serialVersionUID = 1L
    }

override fun toString(): String {
        return this::class.java.name + "#" + id
    }

    /* https://vladmihalcea.com/the-best-way-to-implement-equals-hashcode-and-tostring-with-jpa-and-hibernate/ */
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is GeneralInfo) return false
        return id != null && id == other.id
    }

    override fun hashCode(): Int {
        return javaClass.hashCode()
    }

}
