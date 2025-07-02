package io.zenwave360.example.clinicaltool.modules.termsandconditions.domain

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
@Entity
@Table(name = "terms_and_conditions")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
data class TermsAndConditions(
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)

    var id: Long? = null,

    @Version
    var version: Int? = null,

    @NotNull@Column(name = "content", nullable = false) @Lob 
    var content: String?  = null,

    /**
    * language code
    */
    @NotNull @Size(max = 3)@Column(name = "lang", nullable = false, length = 3)
    var lang: String?  = null,

    /**
    * Arbitrary version string
    */
    @NotNull@Column(name = "content_version", nullable = false, unique = true)
    var contentVersion: String?  = null,

    /**
    * Date when the terms and conditions are valid (inclusive)
    */
    @NotNull@Column(name = "start_date", nullable = false, unique = true)
    var startDate: LocalDate?  = null



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
        if (other !is TermsAndConditions) return false
        return id != null && id == other.id
    }

    override fun hashCode(): Int {
        return javaClass.hashCode()
    }

}
