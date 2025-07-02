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
@Table(name = "accepted_terms_and_conditions")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
data class AcceptedTermsAndConditions(
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)

    var id: Long? = null,

    @Version
    var version: Int? = null,

    @NotNull@Column(name = "user_id", nullable = false) @org.hibernate.annotations.NaturalId 
    var userId: Long?  = null,

    @NotNull@Column(name = "terms_and_conditions_id", nullable = false)
    var termsAndConditionsId: Long?  = null,

    @NotNull@Column(name = "accepted_date", nullable = false)
    var acceptedDate: Instant?  = null



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
        if (other !is AcceptedTermsAndConditions) return false
        return id != null && id == other.id
    }

    override fun hashCode(): Int {
        return javaClass.hashCode()
    }

}
